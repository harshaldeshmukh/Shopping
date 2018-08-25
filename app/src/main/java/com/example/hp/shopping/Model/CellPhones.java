package com.example.hp.shopping.Model;

public class CellPhones {
    public  String category;
    public  String category_id;

    public CellPhones(Builder builder) {
        category=builder.mcategory;
        category_id=builder.mcategory_id;

    }

    public static class Builder {
      private   String mcategory,mcategory_id;

        public Builder setCategory(String category) {
            mcategory = category;
            return  Builder.this;
        }

        public Builder setCategory_id(String category_id) {
            mcategory_id = category_id;
            return  Builder.this;
        }

        public CellPhones build() {
            return new CellPhones(CellPhones.Builder.this);
        }
    }



}
