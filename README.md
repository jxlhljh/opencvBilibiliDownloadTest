@[TOC](Javaʹ��Opencv���д�ͼ��Сͼ��ʹ������ͼ���ܽ���bilibili��Ƶ���ذ���)
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

���Ľ�����Opencv��windows�µİ�װ����ʹ��Java����Opencv�����д�ͼ��Сͼ���ԣ����ͨ��Ӧ�ô�ͼ��Сͼ������ʵ��bilibili����Ƶ���ء�
# һ��Opencv��ͼ��Сͼ˵��
`��������chatGpt3.5ģ�͵Ļش�`
>Opencv��һ�Դ�ļ�����Ӿ��⣬��������ͼ����Ŀ���⡢����ʶ���������Opencv�У�����ʹ��ģ��ƥ���㷨��ʵ�ִ�ͼ��Сͼ�Ĺ��ܡ�
ģ��ƥ���㷨��һ�ֻ������ؼ����ƥ���㷨�����Ļ���˼�����ڴ�ͼ��Ѱ����Сͼ���Ƶ����򡣾���ʵ�ֹ������£�

>��ȡ��ͼ��Сͼ����������ת��Ϊ�Ҷ�ͼ��
����һ���������ڣ��ڴ�ͼ�л��������������е�������Сͼ���бȽϡ�
���㴰����������Сͼ���صĲ��죬�õ�һ��ƥ��ȡ�
��ƥ��ȱ��浽һ�������У�����Ĵ�С���ͼ��ͬ��
�ھ������ҵ����ƥ��ȵ�λ�ã���ΪСͼ�ڴ�ͼ�е�λ�á�
Opencv�ṩ�˶���ģ��ƥ���㷨������ƽ����ƥ�䡢��һ��ƽ����ƥ�䡢���ƥ��͹�һ�����ƥ��ȡ���ͬ���㷨�����ڲ�ͬ�ĳ�������Ҫ����ʵ�����ѡ����ʵ��㷨��

>��֮��Opencv�ṩ��ǿ���ͼ�����ܣ�����ʵ�ִ�ͼ��Сͼ�ȶ���Ӧ�á�
# ����Opencv��window��װ
## 1.����windows�µİ�װ��
>������·���ǣ�[https://opencv.org/](https://opencv.org/)
>�����ص���4.2.0�����ص�ַ��������Ϊ��[https://udomain.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe](https://udomain.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe)
��������(�ǹ������ٶȿ�Щ)��[https://nchc.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe](https://nchc.dl.sourceforge.net/project/opencvlibrary/4.2.0/opencv-4.2.0-vc14_vc15.exe)
## 2.��װ
˫��opencv-4.2.0-vc14_vc15.exe��װ���ɣ������ﰲװ��D:\ProgramFiles\opencv
��װ���Ŀ¼�磺
![���������ͼƬ����](https://img-blog.csdnimg.cn/b3020d5c0e4d4bf787ab1c5bf7a88efe.png)
## 3.Java��Opencv���ز���
>����opencv-420.jar����
�½�һ��Java��maven���̣�Ȼ�󽫰�װĿ¼�µ�`build\java\opencv-420.jar`���Ƶ����̵�libĿ¼
![���������ͼƬ����](https://img-blog.csdnimg.cn/c0067ed03be44b2889d3a86305b2cb9c.png)
>pom.xml�в��ñ������뷽ʽ���£�
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
>��`build\java\x64\opencv_java420.dll`����java���̵�resoureĿ¼��
![���������ͼƬ����](https://img-blog.csdnimg.cn/3523d46558ce49e886e4a0357d896ae2.png)

>������Լ���Opencv�Ƿ�����
```c
package cn.lihua;
import java.net.URL;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class OpencvTest {
		public static void main(String[] args) throws InterruptedException {
		// ���ض�̬�⣬������ԴĿ¼�£��Ƽ�
        URL url = ClassLoader.getSystemResource("opencv_java420.dll");
        //��һ�ּ��صķ�ʽ�����ڻ��������У���Ҫ����
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load(url.getPath());
        Mat source = Imgcodecs.imread("images/source.jpg");
        HighGui.imshow("����", source);
		HighGui.waitKey();
	}
}
```
`ִ�д��������ܴ�ͼ����opencv����������`
# ����Java��ͨ��Opencv����ģ��ƥ���ͼ��Сͼ
>Java�н��д�ͼ��Сͼ��Ҫ���²��裺
>1.����Opencv��
>2.����ͼ��
>3.����ģ��ƥ��
>4.��ȡƥ����
>5.����ƥ����
>6.��ʾ���
>`��Ӧ�Ĵ������£�`
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
			
		// 1.����Opencv��
		URL url = ClassLoader.getSystemResource("opencv_java420.dll");
		System.load(url.getPath());

		// 2.����ͼ��
		Mat src = Imgcodecs.imread(sourceImg);// ��ƥ��ͼƬ
		Mat template = Imgcodecs.imread(smallImg);// ��ȡƥ��ģ��
		
		// 3.����ģ��ƥ��
		// ����һ�����ͼ��
		Mat outputImage = new Mat(src.rows(), src.cols(), src.type());
		Imgproc.matchTemplate(src, template, outputImage, Imgproc.TM_CCOEFF_NORMED);

		// 4.��ȡƥ����,�������ƥ��ֵ
		Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
		Point matchLoc = result.maxLoc;
		double similarity = result.maxVal; //ƥ���
		int x = (int) matchLoc.x; //Сͼ���ͼ�е�x����
		int y = (int) matchLoc.y; //Сͼ���ͼ�е�y����
		System.out.println(x + "," + y + " similarity: " + similarity);
		//�����ҵ��Ľ�����Ͽ��
		Imgproc.rectangle(src,new Point(x,y),new Point(x+template.cols(),y+template.rows()),
				new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
     
	     //5.��ʾ���
		HighGui.imshow("ģ��ƥ��", src);
		HighGui.waitKey();
		
	}

}
```

`source.bmp`
![���������ͼƬ����](https://img-blog.csdnimg.cn/8e504bf25ea943698805506cba4bf47e.png =500x)
`smallImg.bmp`
![���������ͼƬ����](https://img-blog.csdnimg.cn/51728c2bf939427db2e92a2428e5ebda.png =46x)
`ģ��ƥ���`
![���������ͼƬ����](https://img-blog.csdnimg.cn/3c28d08384c64388b5956b41ded695fb.png =600x)
# �ġ����ж�ͼ����
>�������еĲ�ѯ�ǽ�ƥ���ϵ���ѵ���ͼ���أ���ʱ������ͼ�л��ж����Ҳϣ���ܰ����еĶ����أ������ڲ�ѯ����һ���󣬲��ϵĵݹ�ü�������ƥ�䣬���շ������еģ�`ʾ����������`��
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
 * ���ض��λ�õ�����
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
		
		// 1.����Opencv��
		URL url = ClassLoader.getSystemResource("opencv_java420.dll");
		System.load(url.getPath());

		// 2.����ͼ��
		Mat largeImage = Imgcodecs.imread(sourceImg);//��ƥ��ͼƬ
		Mat smallImage = Imgcodecs.imread(smallImg);// ��ȡƥ��ģ��1
		
		// �������ͼ��
        Mat outputImage = new Mat(largeImage.rows(), largeImage.cols(), largeImage.type());
        
        // ����ģ��ƥ��
        Imgproc.matchTemplate(largeImage, smallImage, outputImage, Imgproc.TM_CCOEFF_NORMED);
        
        // ����ƥ����ֵ
        double threshold = 0.8;
        
        // ѭ����������ƥ����
        while (true) {
            // �������ƥ��ֵ
            Core.MinMaxLocResult result = Core.minMaxLoc(outputImage);
            Point matchLoc = result.maxLoc;
            double maxVal = result.maxVal;
            
            // ���ƥ��ֵС����ֵ�����˳�ѭ��
            if (maxVal < threshold) {
                break;
            }
            
            System.out.println(matchLoc.x + "," + matchLoc.y + " similarity: " + maxVal);
            
            // �ڴ�ͼ�б��ƥ��λ��
            Imgproc.rectangle(largeImage, matchLoc, new Point(matchLoc.x + smallImage.cols(),
                    matchLoc.y + smallImage.rows()), new Scalar(0, 0, 255), 2);
            
            // ��ƥ��λ�õ�ֵ����Ϊ0���Ա���һ��ƥ��
            Imgproc.rectangle(outputImage, matchLoc, new Point(matchLoc.x + smallImage.cols(),
                    matchLoc.y + smallImage.rows()), new Scalar(0, 0, 0), -1);
        }
		
		HighGui.imshow("ģ��ƥ��", largeImage);
		HighGui.waitKey();
		
	}
}

```
`big.bmp`
![���������ͼƬ����](https://img-blog.csdnimg.cn/ffb9362023694592b7b10f10079bd425.png =300x)
`small.bmp`
![���������ͼƬ����](https://img-blog.csdnimg.cn/262cbdacc3bd4437935f5e826c470be2.bmp)
`ƥ������`
![���������ͼƬ����](https://img-blog.csdnimg.cn/476f5637fbde4118b5fcffca159b9787.png =300x)
# �壺��������bilibili��Ƶ
>��������Ĳ��Ժ����Ǿ߱���ͨ����ͼ��Сͼ������������������ͨ��ʵ��һ�£�д����bilibili��վ����Ƶ����������

>˼·��
>1.bilibili��վĬ��û��������Ƶ�Ĳ�������Ȱ�װ���ز��
>2.���ϳ���������Ƶ���ҳ��������ؿ����Զ��������Ĳ���
>3.ͨ����ͼ��Сͼ���ܣ������Java��Robot��ʵ���Զ����ҵ�������Ƶ��Ҫ�Ķ�Ӧͼ���λ�ã�Ȼ��ģ�������ﵽ�Զ����ص�Ч����
## 1.bilibili��վ��װ������Ƶ���
>��https://bilibilihelper.com/��վ���в������
![���������ͼƬ����](https://img-blog.csdnimg.cn/fc6869c08fd24adeab29dd689340a040.png)
![���������ͼƬ����](https://img-blog.csdnimg.cn/217463a25b5e44e09d2fac03bb529b52.png)

`�õ�bilibili-helper-u-2.5.23.8.zip���߲����`
>chrome�������ַ������`chrome://extensions/`������ģʽ���ϣ��϶�bilibili-helper-u-2.5.23.8.zip����������а�װ
![���������ͼƬ����](https://img-blog.csdnimg.cn/8ec5cf51bf2a455f804d3b8a2621846f.png)

![���������ͼƬ����](https://img-blog.csdnimg.cn/df98c0624f3c4158a152b92394d26f46.png)
��װ�ɹ��󣬵�һ����Ƶ����һ����Ҫ���������Ƶ�����Ͻǻ�������ز����Сͼ��
![���������ͼƬ����](https://img-blog.csdnimg.cn/4765c262afde41af9b72d20d9aa12536.png)
## 2.ҵ�����̷���
����һ����Ƶ�Ĳ������£����Կ�����������������
`���Ҫ���ص���Ƶ���ӣ������ϽǵĲ��ͼ�꣬�ڵ����Ĵ����е��1080P����ͼ�꣬�ٵ�һ��ͼ�����ֵ�����أ��ٵ�һ�µ�����ؼ���`
![���������ͼƬ����](https://img-blog.csdnimg.cn/e820c8d2446746daa330ea5bb9c1baea.gif)
������Ƶ���ӵ����ع����ǣ�
`ǰ��P1��P2��P3��P4��ƵҪ�����λ����Ҫ��λ����5����Ƶ��ʼ�������ƵҪ�����λ�ö��Ǻ͵�5����Ƶһ��`��ͨ��������ɣ����ǿ��Խ�P1-P4��Ƶ�ĵ��λ�ã�ͨ����ͼ��Сͼ��������λ��P5�ͺ������Ƶ��ֱ���þ���λ�ö�λ���е����`����ʵ�������þ��Զ�λ������Ҫ����ʾ����һ��Opencv�Ĵ�ͼ��Сͼ���ܣ�`
![���������ͼƬ����](https://img-blog.csdnimg.cn/4c320c47f44a4c3eb997054d5d2f0f7a.gif)
��������������������Ƶ���ص�ҵ���߼�����
>1.��P1-P4����Ƶ�����ӽغ�Сͼ�����Ͻǲ��ͼʾ��1080���塢������ص�ͼ��غ�Сͼ��������������ƥ��ʹ��
>2.P1-P4����Ƶ���أ�ͨ����ͼ��Сͼ���ж�λ��ͨ��Java��Robot�����ģ����ʵ����Ƶ����
>3.P5���Ժ����Ƶ��ͨ��ֱ�Ӿ��Զ�λ����Ļλ�õ��������������
>4.��Ƶ���ļ���Ҳ��ͨ�����Զ�λ���أ���P5��Ƶ��λ�ò���ͬ��Ҳ��Ҫ����һ��,����ͨ�����Զ�λ����`�˰�����Ҫ��Ϊ����ϰopencv��ͼ��Сͼ�����е�������ʵ����ͨ�����Զ�λλ������`��

`��ͼΪ��󼸼��ĵ�������Կ��������Ǻ�P5����һ����λ�ã���Ҫÿ�������ƶ�һ�еľ���`
![���������ͼƬ����](https://img-blog.csdnimg.cn/60527290981e431d907704c6a96b75e4.gif)
## 3.����ʵ��
## 3.0 �Ƚ���Ҫ�ļ���ͼ
![���������ͼƬ����](https://img-blog.csdnimg.cn/c35464a7a2e6407a874fc2fbe4b465f4.png)
`ע����������ǰ�������ĵ��������½�ͼ`
## 3.1����Ƭ��
`����OpencvService�ӿ�����ʵ�ִ�ͼ��Сͼ`
```java
package cn.lihua.modules.bilibili.service;

import java.util.List;

import cn.lihua.modules.bilibili.model.FindImgDto;

public interface OpencvService {

	/**
	 * ��ͼ��Сͼ
	 * @param bigImg
	 * @param smallImg
	 * @return
	 */
	FindImgDto templateMatching(String bigImg,String smallImg);
	
	/**
	 * ��ͼ��Сͼ
	 * @param bigImg
	 * @param smallImg
	 * @param thresholdValue ƥ���
	 * @return
	 */
	FindImgDto templateMatching(String bigImg,String smallImg,Double thresholdValue);
	
	/**
	 * ��ͼ��Сͼ�����ض��
	 * @param bigImg
	 * @param smallImg
	 * @param thresholdValue ƥ���
	 * @return
	 */
	List<FindImgDto> templateMultiMatching(String bigImg,String smallImg,Double thresholdValue);
	
	List<FindImgDto> templateMultiMatching(String bigImg,String smallImg);

}

```
`����BilibiliVideoDownService������Ƶ������`
```java
/**
 * ����Bilibili��Ƶ
 * @author jxlhl
 */
public interface BilibiliVideoDownService {
	
	public void exportStart() throws Exception;

}
```
`Robot������ģ���ƶ��͵�����`
```java
		try {
			robot = new Robot();//���Ļ������࣬���̻�����¼����ط�ִ�С�
		} catch (AWTException e) {
			logger.error("error",e);
		}
```
`ȫ����ͼ������ÿ�δ�ͼ��Сͼʱʹ��`
```java
	/**
	 * ȫ����ͼ
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
`����ƶ������Ĵ���`
```java
	/*
	 * �Ƶ�ָ����λ�ò�������
	 * 
	 */
	private void mouseMoveAndClick(int clientX, int clientY) throws InterruptedException{
		mouseMove(clientX, clientY);
		robot.delay(1000);//�漴����0-2��
		mouseClick();
	}
	
	/**
	 * ���������
	 * @throws InterruptedException 
	 */
	private void mouseClick() throws InterruptedException{
		robot.mousePress(InputEvent.BUTTON1_MASK);//���
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);//���
	}

	/**
	 * �ƶ���굽ָ��������
	 */
	private void mouseMove(int clientX, int clientY){
		//��̫��������Ϊʲô����д������һ�д��������ű���������΢�����λ��׼ȷ�����⡣
		robot.mouseMove(-1, -1);
		//�ҵĵ�����200%,��˳���2
		String screenPercent = "200";
		double rate = Integer.parseInt(screenPercent)/100d;
		//����һ����������������
		robot.mouseMove((int)(clientX/rate) , (int)(clientY/rate));
	}
```
`����ĳһ����Ƶ���߼�����`
```java
	/**
	 * ���ص�{pageNumber}����Ƶ
	 * @param pageNumber
	 * @param totalCount
	 * @throws Exception
	 */
	private void downLoad(int pageNumber,int totalCount) throws Exception{
		
		Thread.sleep(3000l);
		
		logger.info("��ʼ���ص�{}����Ƶ��ʣ��{}��",pageNumber,(totalCount-pageNumber));
		
		//��ͼȫ����Ȼ��д��images/fullScreen.jpg
		createScreenCapture();
		//���д�ͼ��Сͼ����
		FindImgDto findImgDto = null;
		
		//ǰ���4����Ƶ��ͨ����ͼ��Сͼ��λλ��
		if(pageNumber<=4){
			findImgDto = opencvService.templateMatching(fullScreen,"images/template/P"+pageNumber+".bmp");
			logger.info(JsonUtil.toJSONString(findImgDto));
			//�ҵ���������ʱ��x�������ҿ�100�����Ե���ø�����Щ
			mouseMoveAndClick(findImgDto.getClientX() + 100 , findImgDto.getClientY());
			
		}else{
			
			if(pageNumber <= 195){
				//��5-��195����Ƶ������͵�5����Ƶ���ڵ�λ�ü��ɣ�1768,1011(����Ҫ������ĵ����϶�Ӧ��λ��)
				mouseMoveAndClick(1768,1011);
			}else{
				
				//���5����Ƶ���⴦��ֱ�Ӷ�λ���е��
				//��196����Ƶ
				if(pageNumber == 196){
					mouseMoveAndClick(1768,1080);//(����Ҫ������ĵ����϶�Ӧ��λ��)
				}
				//��197����Ƶ
				if(pageNumber == 197){
					mouseMoveAndClick(1768,1150);//(����Ҫ������ĵ����϶�Ӧ��λ��)
				}
				if(pageNumber == 198){
					mouseMoveAndClick(1768,1220);//(����Ҫ������ĵ����϶�Ӧ��λ��)
				}
				if(pageNumber == 199){
					mouseMoveAndClick(1768,1280);//(����Ҫ������ĵ����϶�Ӧ��λ��)
				}
				if(pageNumber == 200){
					mouseMoveAndClick(1768,1340);//(����Ҫ������ĵ����϶�Ӧ��λ��)
				}
				
			}
			
		}
		
		//���ϵȴ��������Ͻǵ����ز��ͼ��
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click1);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
		}
		
		//���ϵȴ�����1080����ͼ��
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click2);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
			robot.delay(100);
			mouseMove(findImgDto.getClientX()+300, findImgDto.getClientY());
		}
		
		//���ϵȴ����ҵ������ͼ��
		findImgDto = new FindImgDto();
		while(!findImgDto.isIfFind()){
			Thread.sleep(3000l);
			createScreenCapture();
			findImgDto = opencvService.templateMatching(fullScreen,click3);
			mouseMoveAndClick(findImgDto.getClientX() , findImgDto.getClientY());
			robot.delay(100);
			mouseMove(findImgDto.getClientX()+300, findImgDto.getClientY());
		}
		
		//�������ͼ�����Ҫ�ٵ�һ�����ϽǵĲ��ͼ�긴ԭ
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

`����������Ƶ���߼�����`
```java
	public void exportStart() throws Exception {
		
		//0.����Bilibili��Ƶ�Զ�������
		logger.info("BilibiliVideoDownService Start....");
		
		//��Ϣ5��ȴ������������л���������վҳ��
		Thread.sleep(5000l);
		
		//��Ҫ���ص���Ƶ����
		int totalCount = 200;
		try {
			
			//��������ÿһ����Ƶ
			for(int i =1 ;i<=totalCount;i++){
				downLoad(i,totalCount);
			}
			
			//�����ж��Ƿ��������
			ifFinishedDownload();

			logger.info("all completed."); 
			
		} catch (Exception e) {
			logger.error("errror",e);
		} finally {
			
		}
		
		logger.info("BilibiliVideoDownService success..");
		
	}
```

## 3.2 ����Ч����ͼ
![���������ͼƬ����](https://img-blog.csdnimg.cn/1ee649c31cc046538404aa219c7232f0.gif)## 3.3 Դ������
github: https://github.com/jxlhljh/opencvBilibiliDownloadTest.git
gitee: https://gitee.com/jxlhljh/opencvBilibiliDownloadTest.git