package com.boardjunit5practice.board.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardForm {
    public static class Request{
        @Getter
        @NoArgsConstructor
        public static class Register {
            private String title;
            private String content;
            private Long hit;

            public Register(String title, String content) {
                this.title = title;
                this.content = content;
            }
        }

        @Getter
        @NoArgsConstructor
        public static class Modify{
            private String title;
            private String content;
            private Long hit;

            public Modify(String title, String content, Long hit) {
                this.title = title;
                this.content = content;
                this.hit = hit;
            }
        }
    }

    public static class Response{

        @Getter
        public static class Find{
            private String title;
            private String content;
            private Long hit;

            public Find(String title, String content, Long hit) {
                this.title = title;
                this.content = content;
                this.hit = hit;
            }
        }
    }
}
