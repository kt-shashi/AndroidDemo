package com.shashi.listviewdemo2;

class ModelClassForData {

    int image;
    String fruitName;
    String fruitTaste;

    public ModelClassForData(int image, String fruitName, String fruitTaste) {
        this.image = image;
        this.fruitName = fruitName;
        this.fruitTaste = fruitTaste;
    }

    public int getImage() {
        return image;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String getFruitTaste() {
        return fruitTaste;
    }
}
