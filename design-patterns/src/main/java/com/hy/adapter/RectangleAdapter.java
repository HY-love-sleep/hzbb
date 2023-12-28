package com.hy.adapter;

/**
 * Description: 矩形适配器
 * 将Legacy类适配到Shape接口上
 * Author: yhong
 * Date: 2023/12/28
 */
public class RectangleAdapter implements Shape{
    private LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void draw(int x, int y, int width, int height) {
        int x2 = x + width;
        int y2 = y + height;
        legacyRectangle.display(x, x2, y, y2);
    }
}
