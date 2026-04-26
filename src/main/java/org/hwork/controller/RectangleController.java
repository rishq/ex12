package org.hwork.controller;

import org.hwork.entity.Rectangle;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rectangle")
public class RectangleController {

    @PostMapping("/create")
    public Rectangle createRectangle(@RequestBody Rectangle rectangle) {
        return rectangle;
    }

    @GetMapping("/test")
    public List<String> testRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();
        
        Rectangle r1 = new Rectangle();
        r1.setLength(5.0);
        r1.setWidth(10.0);
        
        Rectangle r2 = new Rectangle();
        r2.setLength(7.0);
        r2.setWidth(7.0);
        
        rectangles.add(r1);
        rectangles.add(r2);

        List<String> results = new ArrayList<>();
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle r = rectangles.get(i);
            results.add("Фигура " + (i + 1) + ": Площадь=" + r.calculateArea() + 
                        ", Периметр=" + r.calculatePerimeter() + 
                        ", Квадрат=" + (r.isSquare() ? "Да" : "Нет"));
        }
        return results;
    }
}
