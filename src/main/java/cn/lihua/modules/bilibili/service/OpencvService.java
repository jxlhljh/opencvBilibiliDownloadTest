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
