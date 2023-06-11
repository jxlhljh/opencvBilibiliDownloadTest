@[TOC](Java使用Opencv进行大图找小图并使用其找图功能进行bilibili视频下载案例)
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

本文将介绍Opencv在windows下的安装，并使用Java操作Opencv进行行大图找小图测试，最后通过应用大图找小图功能来实现bilibili的视频下载。
# 一、Opencv大图找小图说明
`以下来自chatGpt3.5模型的回答：`
>Opencv是一款开源的计算机视觉库，可以用于图像处理、目标检测、人脸识别等领域。在Opencv中，可以使用模板匹配算法来实现大图找小图的功能。
模板匹配算法是一种基于像素级别的匹配算法，它的基本思想是在大图中寻找与小图相似的区域。具体实现过程如下：

>读取大图和小图，并将它们转换为灰度图像。
定义一个滑动窗口，在大图中滑动，并将窗口中的像素与小图进行比较。
计算窗口中像素与小图像素的差异，得到一个匹配度。
将匹配度保存到一个矩阵中，矩阵的大小与大图相同。
在矩阵中找到最大匹配度的位置，即为小图在大图中的位置。
Opencv提供了多种模板匹配算法，包括平方差匹配、归一化平方差匹配、相关匹配和归一化相关匹配等。不同的算法适用于不同的场景，需要根据实际情况选择合适的算法。

>总之，Opencv提供了强大的图像处理功能，可以实现大图找小图等多种应用。
# 二、Opencv的window安装
## 1.下载windows下的安装包
>官网的路径是：[https://opencv.org/](https://opencv.org/)
>我下载的是4.2.0，下载地址（官网）为：[https://udomain.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe](https://udomain.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe)
或者链接(非官网，速度快些)：[https://nchc.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe](https://nchc.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe)
## 2.安装
双击opencv-4.2.0-vc14_vc15.exe安装即可，我这里安装到D:\ProgramFiles\opencv
安装后的目录如：
![在这里插入图片描述](https://img-blog.csdnimg.cn/b3020d5c0e4d4bf787ab1c5bf7a88efe.png)
## 3.Java中Opencv加载测试
>引入opencv-420.jar包：
新建一个Java的maven工程，然后将安装目录下的`build\java\opencv-420.jar`复制到工程的lib目录
![在这里插入图片描述](https://img-blog.csdnimg.cn/c0067ed03be44b2889d3a86305b2cb9c.png)
>pom.xml中采用本地引入方式如下：
```c
		<!-- opencv start -->
		<dependency>
			<groupId>cn.gzsendi</groupId>
			<artifactId>opencv-420</artifactId>
			<version>0.0.1</version>
			<scope>system</scope>
			<systemPath>${pom.basedir}/lib/opencv-420.jar</systemPath>
		</dependency>
		<!-- opencv end -->
```
>将`build\java\x64\opencv_java420.dll`放在java工程的resoure目录下
![在这里插入图片描述](https://img-blog.csdnimg.cn/3523d46558ce49e886e4a0357d896ae2.png)

>代码测试加载Opencv是否正常
```c
package cn.lihua;
import java.net.URL;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class OpencvTest {
		public static void main(String[] args) throws InterruptedException {
		// 加载动态库，放在资源目录下，推荐
        URL url = ClassLoader.getSystemResource("opencv_java420.dll");
        //另一种加载的方式，放在环境变量中，需要配置
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load(url.getPath());
        Mat source = Imgcodecs.imread("images/source.jpg");
        HighGui.imshow("测试", source);
		HighGui.waitKey();
	}
}
```
`执行代码后，如果能打开图像，则opencv环境正常。`
# 三、Java中通过Opencv进行模板匹配大图找小图
>Java中进行大图找小图主要如下步骤：
>1.导入Opencv库
>2.加载图像
>3.进行模板匹配
>4.获取匹配结果
>5.绘制匹配结果
>6.显示结果
>`对应的代码如下：`
```c
package cn.lihua.modules.test;

import java.net.URL;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TemplateMatchingTest {
	
	public static void main(String[] args) {
		String sourceImg = "images/source.bmp";
		String smallImg = "images/smallImg.bmp";
		new TemplateMatchingTest().templateMatching(sourceImg, smallImg);
	}
	
	public void templateMatching(String sourceImg,String smallImg) {
			
		// 1.导入Opencv库
		URL url = ClassLoader.getSystemResource("opencv_java420.dll");
		System.load(url.getPath());

		// 2.加载图像
		Mat src = Imgcodecs.imread(sourceImg);// 待匹配图片
		Mat template = Imgcodecs.imread(smallImg);// 获取匹配模板
		
		// 3.进行模板匹配
		// 创建一个输出图像
		Mat outputImage = new Mat(src.rows(), src.cols(), src.type());
		Imgproc.matchTemplate(src, template, outputImage, Imgproc.TM_CCOEFF_NORMED);

		// 4.获取匹配结果,查找最大匹配值
		Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
		Point matchLoc = result.maxLoc;
		double similarity = result.maxVal; //匹配度
		int x = (int) matchLoc.x; //小图大大图中的x坐标
		int y = (int) matchLoc.y; //小图大大图中的y坐标
		System.out.println(x + "," + y + " similarity: " + similarity);
		//将查找到的结果标上框框
		Imgproc.rectangle(src,new Point(x,y),new Point(x+template.cols(),y+template.rows()),
				new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
     
	     //5.显示结果
		HighGui.imshow("模板匹配", src);
		HighGui.waitKey();
		
	}

}
```

`source.bmp`
![在这里插入图片描述](https://img-blog.csdnimg.cn/8e504bf25ea943698805506cba4bf47e.png =500x)
`smallImg.bmp`
![在这里插入图片描述](https://img-blog.csdnimg.cn/51728c2bf939427db2e92a2428e5ebda.png =46x)
`模板匹配后：`
![在这里插入图片描述](https://img-blog.csdnimg.cn/3c28d08384c64388b5956b41ded695fb.png =600x)
# 四、进行多图查找
>三步骤中的查询是将匹配上的最佳的子图返回，有时可能子图中会有多个，也希望能把所有的都返回，可以在查询到第一个后，不断的递归裁剪并继续匹配，最终返回所有的，`示例代码如下`：
```c
package cn.lihua.modules.test;

import java.net.URL;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 返回多个位置的例子
 * @author liujh
 *
 */
public class TemplateMatchingMultiResultExample {
	
	public static void main(String[] args) {
		String sourceImg = "images/big1.bmp";
		String smallImg = "images/small1.bmp";
		new TemplateMatchingMultiResultExample().templateMultiMatching(sourceImg, smallImg);
	}

	public void templateMultiMatching(String sourceImg,String smallImg) {
		
		// 1.导入Opencv库
		URL url = ClassLoader.getSystemResource("opencv_java420.dll");
		System.load(url.getPath());

		// 2.加载图像
		Mat largeImage = Imgcodecs.imread(sourceImg);//待匹配图片
		Mat smallImage = Imgcodecs.imread(smallImg);// 获取匹配模板1
		
		// 创建输出图像
        Mat outputImage = new Mat(largeImage.rows(), largeImage.cols(), largeImage.type());
        
        // 进行模板匹配
        Imgproc.matchTemplate(largeImage, smallImage, outputImage, Imgproc.TM_CCOEFF_NORMED);
        
        // 设置匹配阈值
        double threshold = 0.8;
        
        // 循环遍历所有匹配结果
        while (true) {
            // 查找最大匹配值
            Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
            Point matchLoc = result.maxLoc;
            double maxVal = result.maxVal;
            
            // 如果匹配值小于阈值，则退出循环
            if (maxVal < threshold) {
                break;
            }
            
            System.out.println(matchLoc.x + "," + matchLoc.y + " similarity: " + maxVal);
            
            // 在大图中标出匹配位置
            Imgproc.rectangle(largeImage, matchLoc, new Point(matchLoc.x + smallImage.cols(),
                    matchLoc.y + smallImage.rows()), new Scalar(0, 0, 255), 2);
            
            // 将匹配位置的值设置为0，以便下一次匹配
            Imgproc.rectangle(outputImage, matchLoc, new Point(matchLoc.x + smallImage.cols(),
                    matchLoc.y + smallImage.rows()), new Scalar(0, 0, 0), -1);
        }
		
		HighGui.imshow("模板匹配", largeImage);
		HighGui.waitKey();
		
	}
}

```
`big.bmp`
![在这里插入图片描述](https://img-blog.csdnimg.cn/ffb9362023694592b7b10f10079bd425.png =300x)
`small.bmp`
![在这里插入图片描述](https://img-blog.csdnimg.cn/262cbdacc3bd4437935f5e826c470be2.bmp)
`匹配结果：`
![在这里插入图片描述](https://img-blog.csdnimg.cn/476f5637fbde4118b5fcffca159b9787.png =300x)
# 五：案例下载bilibili视频
>经过上面的测试后，我们具备了通过大图找小图的能力，接下来我们通过实践一下，写程序将bilibili网站的视频下载下来。

>思路：
>1.bilibili网站默认没有下载视频的插件，首先安装下载插件
>2.手上尝试下载视频，找出下载视载可以自动化操作的步骤
>3.通过大图找小图功能，并结合Java的Robot类实现自动化找到下载视频需要的对应图标和位置，然后模拟点击，达到自动下载的效果。
## 1.bilibili网站安装下载视频插件
>上https://bilibilihelper.com/网站进行插件下载
![在这里插入图片描述](https://img-blog.csdnimg.cn/fc6869c08fd24adeab29dd689340a040.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/217463a25b5e44e09d2fac03bb529b52.png)

`得到bilibili-helper-u-2.5.23.8.zip离线插件包`
>chrome浏览器地址栏输入`chrome://extensions/`，开发模式勾上，拖动bilibili-helper-u-2.5.23.8.zip至浏览器进行安装
![在这里插入图片描述](https://img-blog.csdnimg.cn/8ec5cf51bf2a455f804d3b8a2621846f.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/df98c0624f3c4158a152b92394d26f46.png)
安装成功后，点一个视频看（一定需要点击播放视频）右上角会出现下载插件的小图标
![在这里插入图片描述](https://img-blog.csdnimg.cn/4765c262afde41af9b72d20d9aa12536.png)
## 2.业务流程分析
下载一个视频的步骤如下，可以看出来步骤是这样：
`点击要下载的视频链接，点右上角的插件图标，在弹出的窗口中点击1080P高清图标，再点一下图标会出现点击下载，再点一下点击下载即可`
![在这里插入图片描述](https://img-blog.csdnimg.cn/e820c8d2446746daa330ea5bb9c1baea.gif)
所有视频链接的下载规律是：
`前面P1、P2、P3、P4视频要点击的位置需要定位，第5个视频开始后面的视频要点击的位置都是和第5个视频一样`，通过这个规律，我们可以将P1-P4视频的点击位置，通过大图找小图功能来定位，P5和后面的视频就直接用绝对位置定位进行点击，`（其实都可以用绝对定位，但主要想演示就用一下Opencv的大图找小图功能）`
![在这里插入图片描述](https://img-blog.csdnimg.cn/4c320c47f44a4c3eb997054d5d2f0f7a.gif)
这样经过分析后，整个视频下载的业务逻辑如下
>1.将P1-P4的视频的链接截好小图，右上角插件图示、1080高清、点击下载等图标截好小图，供后面程序进行匹配使用
>2.P1-P4的视频下载，通过大图找小图进行定位，通过Java的Robot类进行模拟点击实现视频下载
>3.P5及以后的视频，通过直接绝对定位到屏幕位置点击进行视屏下载
>4.视频最后的几个也简单通过绝对定位下载，和P5视频的位置不相同，也需要处理一下,我们通过绝对定位处理（`此案例主要是为了练习opencv大图找小图，所有的下载其实都能通过绝对定位位置下载`）

`下图为最后几集的点击，可以看到不再是和P5视屏一样的位置，需要每次向下移动一行的距离`
![在这里插入图片描述](https://img-blog.csdnimg.cn/60527290981e431d907704c6a96b75e4.gif)
## 3.代码实现
## 3.0 先截需要的几个图
![在这里插入图片描述](https://img-blog.csdnimg.cn/c35464a7a2e6407a874fc2fbe4b465f4.png)
`注：程序启动前最好在你的电脑上重新截图`
## 3.1代码片段
`定义OpencvService接口用来实现大图找小图`
```java
package cn.lihua.modules.bilibili.service;

import java.util.List;

import cn.lihua.modules.bilibili.model.FindImgDto;

public interface OpencvService {

	/**
	 * 大图找小图
	 * @param bigImg
	 * @param smallImg
	 * @return
	 */
	FindImgDto templateMatching(String bigImg,String smallImg);
	
	/**
	 * 大图找小图
	 * @param bigImg
	 * @param smallImg
	 * @param thresholdValue 匹配度
	 * @return
	 */
	FindImgDto templateMatching(String bigImg,String smallImg,Double thresholdValue);
	
	/**
	 * 大图找小图，返回多个
	 * @param bigImg
	 * @param smallImg
	 * @param thresholdValue 匹配度
	 * @return
	 */
	List<FindImgDto> templateMultiMatching(String bigImg,String smallImg,Double thresholdValue);
	
	List<FindImgDto> templateMultiMatching(String bigImg,String smallImg);

}

```
`定义BilibiliVideoDownService进行视频的下载`
```java
/**
 * 下载Bilibili视频
 * @author jxlhl
 */
public interface BilibiliVideoDownService {
	
	public void exportStart() throws Exception;

}
```
`Robot类用于模拟移动和点击鼠标`
```java
		try {
			robot = new Robot();//核心机器人类，键盘或鼠标事件的重放执行。
		} catch (AWTException e) {
			logger.error("error",e);
		}
```
`全屏截图，用于每次大图找小图时使用`
```java
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
```
`鼠标移动与点击的代码`
```java
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
```
`下载某一个视频的逻辑代码`
```java
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
```

`下载所有视频的逻辑代码`
```java
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
```

## 3.2 程序效果截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/1ee649c31cc046538404aa219c7232f0.gif)## 3.3 源码下载
github: https://github.com/jxlhljh/opencvBilibiliDownloadTest.git
gitee: https://gitee.com/jxlhljh/opencvBilibiliDownloadTest.git