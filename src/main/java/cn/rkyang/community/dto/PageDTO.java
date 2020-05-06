package cn.rkyang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据传输对象
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/6 21:20
 */
@Data
public class PageDTO {

    /**
     * 数据对象
     */
    private List<QuestionDTO> questionDTOList;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 页的显示页码
     */
    private List<Integer> pages = new ArrayList<Integer>();

    /**
     * 是否显示尾页按钮
     */
    private Boolean showLast;

    /**
     * 是否显示首页按钮
     */
    private Boolean showFirst;

    /**
     * 是否显示下一页按钮
     */
    private Boolean showNext;

    /**
     * 是否显示上一页按钮
     */
    private Boolean showOn;

    public void setPageInfo(Integer total, Integer page, Integer size) {
        Integer totalPage;
        if (total % size == 0) {
            totalPage = total / size;
        }else {
            totalPage = total / size + 1;
        }
        if (page > 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;
        pages.add(page);
        for (int i = 0; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showOn = false;
        }else {
            showOn = true;
        }
        //是否展示下一页
        if (page.equals(totalPage)) {
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示首页
        if (pages.contains(1)) {
            showFirst = false;
        }else {
            showFirst = true;
        }
        //是否展示尾页
        if (pages.contains(totalPage)) {
            showLast = false;
        }else {
            showLast = true;
        }
    }
}
