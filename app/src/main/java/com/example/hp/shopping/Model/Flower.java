package com.example.hp.shopping.Model;



public class Flower {

    public  String mcategory,mInstructions,mPhoto,mName;
    public  Double mPrice;
    public  int Pid;

    private Flower(Builder builder){
        mcategory = builder.mcategory;
        mInstructions = builder.mInstructions;
        mPhoto = builder.mPhoto;
        mName = builder.mName;
        mPrice = builder.mPrice;
        Pid = builder.Pid;
    }

    public static class Builder {

        private String mcategory, mInstructions, mPhoto, mName;
        private double mPrice;
        private int Pid;

        public Builder setCategory(String category) {
            mcategory = category;
            return Builder.this;
        }

        public Builder setInstructions(String instructions) {
            mInstructions = instructions;
            return Builder.this;
        }

        public Builder setPhoto(String photo) {
            mPhoto = photo;
            return Builder.this;
        }

        public Builder setName(String name) {
            mName = name;
            return Builder.this;
        }

        public Builder setPrice(double price) {
            mPrice = price;
            return Builder.this;
        }

        public Builder setProductId(int productId) {
            Pid = productId;
            return Builder.this;
        }

        public Flower build() {
            return new Flower(Builder.this);
        }
    }

}
