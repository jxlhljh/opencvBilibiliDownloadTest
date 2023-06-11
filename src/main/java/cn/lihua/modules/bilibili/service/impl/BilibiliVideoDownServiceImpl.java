package cn.lihua.modules.bilibili.service.impl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lihua.modules.bilibili.model.FindImgDto;
import cn.lihua.modules.bilibili.service.BilibiliVideoDownService;
import cn.lihua.modules.bilibili.service.OpencvService;
import cn.lihua.modules.framework.utils.JsonUtil;

@Service
public class BilibiliVideoDownServiceImpl implements BilibiliVideoDownService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OpencvService opencvService;

	private Robot robot = null;
	
	private String fullScreen = "images/fullScreen.jpg";//全屏截图
	private String click1 = "images/template/click1.bmp"; //右上角插件图标
	private String click2 = "images/template/click2.bmp"; //1080高清图标
	private String click3 = "images/template/click3.bmp"; //点击下载的图标
	
	public BilibiliVideoDownServiceImpl(){
		try {
			robot = new Robot();//核心机器人类，键盘或鼠标事件的重放执行。
		} catch (AWTException e) {
			logger.error("error",e);
		}
	}
	
	@Override
	public void exportStart() throws Exception {
		
		//0.下载Bilibili视频自动化操作
		logger.info("BilibiliVideoDownService Start....");
		
		//休息5秒等待程序启动了切换到下载网站页面
		Thread.sleep(5000l);
		
		//需要下载的视频数量
		int totalCount = 200;
		try {
			
			//遍历下载每一个视频
			for(int i =1 ;i<=totalCount;i++){
				downLoad(i,totalCount);
			}
			
			//阻塞判断是否下载完成
			ifFinishedDownload();

			logger.info("all completed."); 
			
		} catch (Exception e) {
			logger.error("errror",e);
		} finally {
			
		}
		
		logger.info("BilibiliVideoDownService success..");
		
	}
	
	/**
	 * 是否完成下载
	 * @throws Exception
	 */
	private void ifFinishedDownload() throws Exception{
		Scanner in = new Scanner(System.in);
		try {
			
	        /**
	         * 最后所有的视频都下载点击完，按理程序可以退出，但我们的下载视频需要时间，所以不一定视频已经下载完，此时如果
	         * 直接退出程序，浏览器将马上就关闭了，那未下载完的视频就不完整，也在最后这里人工处理一下确认已下载完所有视频再退出程序
	        /**人工输入yes来确认已经成功下载完毕*/
			logger.info("is the all download complete? , input yes or no ?");
	        while(true){
	        	if(in.hasNextLine()){
	            	if("yes".equals(in.nextLine())){
	            		break;
	            	}
	            }else{
	            	Thread.sleep(3000l);
	            }
	        }
			
		} catch (Exception e) {
			logger.error("errror",e);
		} finally {
			in.close();
		}
        
	}
	
	/**
	 * 下载第{pageNumber}个视频
	 * @param pageNumber
	 * @param totalCount
	 * @throws Exception
	 */
	private void downLoad(int pageNumber,int totalCount) throws Exception{
		
		Thread.sleep(3000l);
		
		logger.info("开始下载第{}个视频，剩余{}个",pageNumber,(totalCount-pageNumber));
		
		//截图全屏，然后写入images/fullScreen.jpg
		createScreenCapture();
		//进行大图找小图测试
		FindImgDto findImgDto = null;
		
		//前面的4个视频，通过大图找小图定位位置
		if(pageNumber<=4){
			findImgDto = opencvService.templateMatching(fullScreen,"images/template/P"+pageNumber+".bmp");
			logger.info(JsonUtil.toJSONString(findImgDto));
			//找到的坐标点击时，x坐标向右靠100，可以点击得更中心些
			mouseMoveAndClick(findImgDto.getClientX() + 100 , findImgDto.getClientY());
			
		}else{
			
			if(pageNumber <= 195){
				//第5-第195个视频都点击和第5个视频所在的位置即可，1768,1011(你需要换成你的电脑上对应的位置)
				mouseMoveAndClick(1768,1011);
			}else{
				
				//最后5个视频特殊处理，直接定位进行点击
				//第196个视频
				if(pageNumber == 196){
					mouseMoveAndClick(1768,1080);//(你需要换成你的电脑上对应的位置)
				}
				//第197个视频
				if(pageNumber == 197){
					mouseMoveAndClick(1768,1150);//(你需要换成你的电脑上对应的位置)
				}
				if(pageNumber == 198){
					mouseMoveAndClick(1768,1220);//(你需要换成你的电脑上对应的位置)
				}
				if(pageNumber == 199){
					mouseMoveAndClick(1768,1280);//(你需要换成你的电脑上对应的位置)
				}
				if(pageNumber == 200){
					mouseMoveAndClick(1768,1340);//(你需要换成你的电脑上对应的位置)
				}
				
			}
			
		}
		
		//不断等待查找右上角的下载插件图标
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click1);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
		}
		
		//不断等待查找1080高清图标
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click2);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
			robot.delay(100);
			mouseMove(findImgDto.getClientX()+300, findImgDto.getClientY());
		}
		
		//不断等待查找点击下载图标
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click3);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
			robot.delay(100);
			mouseMove(findImgDto.getClientX()+300, findImgDto.getClientY());
		}
		
		//点击下载图标后，需要再点一下右上角的插件图标复原
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click1);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
			robot.delay(100);
		}
		
	}

	/**
	 * 全屏截图
	 */
	private void createScreenCapture() {

		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		try {
			BufferedImage screenShotImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
			File sourceImg = new File("images/fullScreen.jpg");
			ImageIO.write(screenShotImage, "jpg", sourceImg);
		} catch (Exception e) {
			logger.error("error",e);
		}

	}
	
	/*
	 * 移到指定的位置并左键点击
	 * 
	 */
	private void mouseMoveAndClick(int clientX, int clientY) throws InterruptedException{
		mouseMove(clientX, clientY);
		robot.delay(1000);//随即休眠0-2秒
		mouseClick();
	}
	
	/**
	 * 点击鼠标左键
	 * @throws InterruptedException 
	 */
	private void mouseClick() throws InterruptedException{
		robot.mousePress(InputEvent.BUTTON1_MASK);//左键
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);//左键
	}

	/**
	 * 移动鼠标到指定的坐标
	 */
	private void mouseMove(int clientX, int clientY){
		//不太明白这里为什么这样写，但这一行代码结合缩放比例可以稍微解决定位不准确的问题。
		robot.mouseMove(-1, -1);
		//我的电脑是200%,因此除以2
		String screenPercent = "200";
		double rate = Integer.parseInt(screenPercent)/100d;
		//增加一点点的随机数，防检测
		robot.mouseMove((int)(clientX/rate) , (int)(clientY/rate));
	}

}
