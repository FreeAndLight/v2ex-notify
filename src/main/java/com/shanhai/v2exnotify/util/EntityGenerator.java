package com.shanhai.v2exnotify.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 实体生成工具
 *
 * @author herman
 * @date 2020-08-23 11:19
 */
public class EntityGenerator {

	public static void main(String[] args) {

		// 开始生成
		System.out.println("回车开始生成...............");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		entityGenerator();
		System.out.println("生成完成");
	}

	public static void entityGenerator() {


		//======================以下区域为可能需要修改的内容================
		//============================start=========================

		//模块名,不同表可能同属与一个模块

		// 需要生成的表名,可填多个
		String[] tables = new String[]{
				"t_components"
		};

		// 表前缀,可填多个
		String[] tablePrefixs = new String[]{
				"t_"
		};

		// 作者
		String author = "hk" ;
		//=============================end===========================

		// 基础包名
		String basePackageName = "com.shanhai.v2exnotify" ;
		// 获取项目根路径,此处自动获取,无需收到修改
		// 这里写你自己的java目录
		String basePath = "/Users/shanhai/Documents/workspace/v2/v2ex-notify/" ;
//        String basePath = System.getProperty("user.dir");
		String outputDirPath = basePath + "/src/main/java" ;
		String mapperXmlPath = basePath + "/src/main/resources/mapper/" ;

		// 数据库
		String dataBaseUrl = "jdbc:mysql://localhost:3306/v2ex?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true" ;
		String driverName = "com.mysql.cj.jdbc.Driver" ;
		String userName = "root" ;
		String password = "Gpdi2021" ;

		// ------------------------------------------------------------------------------------------------------------

		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outputDirPath);
		//是否覆盖
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(false);
		gc.setAuthor(author);
		//主键生成策略:默认为雪花算法的String类型
		gc.setIdType(IdType.ID_WORKER_STR);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(dataBaseUrl);
		dsc.setDriverName(driverName);
		dsc.setUsername(userName);
		dsc.setPassword(password);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 此处可以修改为您的表前缀
		strategy.setTablePrefix(tablePrefixs);
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setInclude(tables);
		// lombok
		strategy.setEntityLombokModel(true);
		// restController
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(basePackageName);
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setController("controller");
		mpg.setPackageInfo(pc);
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
			}
		};

		String templatePath = "/templates/mapper.xml.vm" ;
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return mapperXmlPath + tableInfo.getEntityName() +
						"Mapper" + StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		// 配置模板,加入这段代码,就xml文件就不会生成在mapper包中
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);
		// 执行生成
		mpg.execute();

	}
}
