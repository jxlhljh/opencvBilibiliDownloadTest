package cn.lihua.modules.bilibili.model;

/**
 * 找图结果的Bean
 * @author liujh
 *
 */
public class FindImgDto {

	/**
	 * 是否已经找到
	 */
	private boolean ifFind;
	
	/**
	 * 匹配的坐标X(中心)
	 */
	private int clientX;
	
	/**
	 * 匹配的坐标Y(中心)
	 */
	private int clientY;
	
	/**
	 * 匹配的坐标X(左上X)
	 */
	private int leftTopX;
	
	/**
	 * 匹配的坐标X(左上Y)
	 */
	private int leftTopY;
	
	/**
	 * 匹配的坐标X(右下X)
	 */
	private int rightDownX;
	
	/**
	 * 匹配的坐标X(右下Y)
	 */
	private int rightDownY;
	
	/**
	 * 宽度
	 */
	private int width;
	
	/**
	 * 高度
	 */
	private int height;
	
	/**
	 * 相似度
	 */
    private double similarity ;
    
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLeftTopX() {
		return leftTopX;
	}

	public void setLeftTopX(int leftTopX) {
		this.leftTopX = leftTopX;
	}

	public int getLeftTopY() {
		return leftTopY;
	}

	public void setLeftTopY(int leftTopY) {
		this.leftTopY = leftTopY;
	}

	public int getRightDownX() {
		return rightDownX;
	}

	public void setRightDownX(int rightDownX) {
		this.rightDownX = rightDownX;
	}

	public int getRightDownY() {
		return rightDownY;
	}

	public void setRightDownY(int rightDownY) {
		this.rightDownY = rightDownY;
	}

	public int getClientX() {
		return clientX;
	}

	public void setClientX(int clientX) {
		this.clientX = clientX;
	}

	public int getClientY() {
		return clientY;
	}

	public void setClientY(int clientY) {
		this.clientY = clientY;
	}

	public boolean isIfFind() {
		return ifFind;
	}

	public void setIfFind(boolean ifFind) {
		this.ifFind = ifFind;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
}
