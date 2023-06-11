package cn.lihua.modules.bilibili.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.lihua.modules.bilibili.model.FindImgDto;
import cn.lihua.modules.bilibili.service.OpencvService;
import cn.lihua.modules.framework.utils.JsonUtil;

@Service
public class OpencvServiceImpl implements OpencvService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public OpencvServiceImpl() {
		// 加载动态库
        URL url = ClassLoader.getSystemResource("opencv_java420.dll");
        System.load(url.getPath());
	}
	
	public FindImgDto templateMatching(String bigImg,String smallImg){
		return templateMatching(bigImg, smallImg,null);
	}

	@Override
	public FindImgDto templateMatching(String bigImg,String smallImg,Double thresholdValue) {
		
        Mat src = Imgcodecs.imread(bigImg);//待匹配图片
		Mat template = Imgcodecs.imread(smallImg);// 获取匹配模板
		
        // 将大图和小图转换为灰度图像
        Mat grayBig = new Mat();
        Mat graySmall = new Mat();
        Imgproc.cvtColor(src, grayBig, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(template, graySmall, Imgproc.COLOR_BGR2GRAY);

        /*
        // 对大图进行二值化处理
        Mat binaryBig = new Mat();
        Imgproc.threshold(grayBig, binaryBig, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat binarySmall = new Mat();
        Imgproc.threshold(graySmall, binarySmall, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);*/
		
		// 创建输出图像
        Mat outputImage = new Mat(src.rows(), src.cols(), src.type());
        
        // 进行模板匹配
        Imgproc.matchTemplate(grayBig, graySmall, outputImage, Imgproc.TM_CCOEFF_NORMED);
        
        // 设置匹配阈值
        
        double threshold = (thresholdValue == null) ? 0.7 : thresholdValue; //设置匹配阈值为0.8,如果有传参数就使用参数中的
        
        // 查找最大匹配值
        Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
        Point matchLoc = result.maxLoc;
        double similarity = result.maxVal;
		int x = (int)matchLoc.x;
		int y = (int)matchLoc.y;
		
		/*Imgproc.rectangle(src,new Point(x,y),new Point(x+template.cols(),y+template.rows()),new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
		HighGui.imshow("模板匹配", src);
		HighGui.waitKey();*/
        
        FindImgDto dto = new FindImgDto();
        dto.setClientX((int)(x + template.cols()/2));
        dto.setClientY((int)(y + template.rows()/2));
        dto.setLeftTopX(x);
        dto.setLeftTopY(y);
        dto.setRightDownX(x + template.cols());
        dto.setRightDownY(y + template.rows());
        dto.setSimilarity(similarity);
        dto.setIfFind(((similarity >= threshold) && x != 0 && y != 0) ? true : false);
        dto.setWidth(template.cols());
        dto.setHeight(template.rows());
        logger.info("templateMatching result : {}" , JsonUtil.toJSONString(dto));
        
        return dto;
		
	}
	
	/**
	 * 默认0.9
	 */
	public List<FindImgDto> templateMultiMatching(String bigImg,String smallImg){
		return templateMultiMatching(bigImg, smallImg, 0.9);
	}
	
	public List<FindImgDto> templateMultiMatching(String bigImg,String smallImg,Double thresholdValue){
		
		Mat src = Imgcodecs.imread(bigImg);//待匹配图片
		Mat template = Imgcodecs.imread(smallImg);// 获取匹配模板
		
        // 将大图和小图转换为灰度图像
        Mat grayBig = new Mat();
        Mat graySmall = new Mat();
        Imgproc.cvtColor(src, grayBig, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(template, graySmall, Imgproc.COLOR_BGR2GRAY);

        /*
        // 对大图进行二值化处理
        Mat binaryBig = new Mat();
        Imgproc.threshold(grayBig, binaryBig, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat binarySmall = new Mat();
        Imgproc.threshold(graySmall, binarySmall, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);*/
		
		// 创建输出图像
        Mat outputImage = new Mat(src.rows(), src.cols(), src.type());
        
        // 进行模板匹配
        Imgproc.matchTemplate(src, template, outputImage, Imgproc.TM_CCOEFF_NORMED);
        
        // 设置匹配阈值
        
        double threshold = (thresholdValue == null) ? 0.8 : thresholdValue; //设置匹配阈值为0.8,如果有传参数就使用参数中的
        
        
        List<FindImgDto> resultList = new ArrayList<FindImgDto>();
        // 循环遍历所有匹配结果
        while (true) {
            // 查找最大匹配值
            Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
            Point matchLoc = result.maxLoc;
            double similarity = result.maxVal;
            int x = (int)matchLoc.x;
    		int y = (int)matchLoc.y;
            
            // 如果匹配值小于阈值，则退出循环
            if (similarity < threshold) {
                break;
            }
            
            //System.out.println(matchLoc.x + "," + matchLoc.y + " similarity: " + maxVal);
            
            // 在大图中标出匹配位置
            Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(),
                    matchLoc.y + template.rows()), new Scalar(0, 0, 255), 2);
            
            // 将匹配位置的值设置为0，以便下一次匹配
            Imgproc.rectangle(outputImage, matchLoc, new Point(matchLoc.x + template.cols(),
                    matchLoc.y + template.rows()), new Scalar(0, 0, 0), -1);
            
            //加入
            FindImgDto dto = new FindImgDto();
            dto.setClientX((int)(x + template.cols()/2));
            dto.setClientY((int)(y + template.rows()/2));
            dto.setLeftTopX(x);
            dto.setLeftTopY(y);
            dto.setRightDownX(x + template.cols());
            dto.setRightDownY(y + template.rows());
            dto.setSimilarity(similarity);
            dto.setIfFind(((similarity >= threshold) && x != 0 && y != 0) ? true : false);
            resultList.add(dto);
            
        }
        
        return resultList;
		
	}

}
