package com.boardjunit5practice.board.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardForm {
    public static class Request{
        @Getter
        @NoArgsConstructor
        public static class register{
            private String title;
            private String content;
            private Long hit;
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
