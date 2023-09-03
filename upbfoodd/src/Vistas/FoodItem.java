/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;


/**
 *
 * @author 57314
 */
public class FoodItem {
        private String name;
        private String description;
        private String imagePath;

        public FoodItem(String name, String description, String imagePath) {
            this.name = name;
            this.description = description;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
    
