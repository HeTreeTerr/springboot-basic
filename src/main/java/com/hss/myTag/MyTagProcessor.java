package com.hss.myTag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.http.HttpServletRequest;

public class MyTagProcessor extends AbstractElementTagProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final int PRECEDENCE = 10000;
    private static final String TAG_NAME = "Permission";

    public MyTagProcessor(String dialectPrefix) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                TAG_NAME,
                // 没有要应用于标签名称的前缀
                false,
                // 无属性名称：将通过标签名称匹配
                null,
                // 没有要应用于属性名称的前缀
                false,
                // 优先(内部方言自己的优先)
                PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        //获取元素名称
        logger.info("元素名称"+tag.getElementCompleteName());
        WebEngineContext context2 = (WebEngineContext)context;
        HttpServletRequest request = context2.getRequest();
        //System.out.println("用户:" + request.getSession().getAttribute("username"));


        IAttribute key = tag.getAttribute("key");
        logger.info("匹配上:" + key.getValue());
        if (key.getValue().equals("admin")) {
            //满足条件，移除自定义，仅让内容显示
            structureHandler.removeTags();
        } else {
            //不满足条件，直接将标签连同内部内容移除
            structureHandler.removeElement();
        }
    }
}
