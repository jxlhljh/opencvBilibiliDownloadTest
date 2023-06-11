package cn.lihua;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import cn.lihua.modules.bilibili.service.BilibiliVideoDownService;

/**
 * 此SpringBoot工程只引用了spring-boot-starter，执行完main方法程序就结束，也不占用端口
 */
@SpringBootApplication
public class OpencvBilibiliApplicationStart {

	public static void main(String[] args) throws Exception {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(OpencvBilibiliApplicationStart.class);
		ConfigurableApplicationContext ctx = builder.headless(false).run(args);
		BilibiliVideoDownService service = ctx.getBean(BilibiliVideoDownService.class);
		service.exportStart();

	}

}
