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
