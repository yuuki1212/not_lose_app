package com.example.kirin_dev31.toreger.domain.models;

import java.util.Date;

public class GridItem {
    public Space space;
    public Item item;

    public static class Space {
        public int id = -1;
        public String name;
        public String url;
        public int main_no;
        public int tree_no;
        public String comment;
        public int capacity;
        public int category_id;
        public int is_show;
        public Date created_at;
        public Date updated_at;
    }

    public static class Item {
        public int id = -1;
        public int space_id;
        public String url;
        public String name;
        public String description;
        public int usage_id;
        public int count;
        public String category_id;
        public int is_show;
        public Date created_at;
        public Date updated_at;
    }
}
