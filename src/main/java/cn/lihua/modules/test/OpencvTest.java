package cn.lihua.modules.test;

import java.net.URL;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class OpencvTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// 加载动态库
        URL url = ClassLoader.getSystemResource("opencv_java420.dll");
        System.load(url.getPath());
        Mat source = Imgcodecs.imread("images/source.bmp");
        HighGui.imshow("测试", source);
		HighGui.waitKey();
		
	}
}
