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
