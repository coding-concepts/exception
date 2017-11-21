
-- ---------------------------------------------------------------------------
--      BOOK COPY TABLE. STORES THE DETAILS OF A BOOK_COPY
-- ---------------------------------------------------------------------------

CREATE TABLE BOOK_COPY (
ID BIGINT NOT NULL  AUTO_INCREMENT, -- BOOK COPY ID
BOOK_ID BIGINT NOT NULL, -- the book id. Foreign key to book table(ben)
PRIMARY KEY (ID)
);