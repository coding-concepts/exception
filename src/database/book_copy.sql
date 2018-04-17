
-- ---------------------------------------------------------------------------
--      BOOK COPY TABLE. STORES THE DETAILS OF A BOOK_COPY
-- ---------------------------------------------------------------------------

CREATE TABLE BOOK_COPY (
ID BIGINT NOT NULL  AUTO_INCREMENT,
BOOK_ID BIGINT NOT NULL, -- Foreign key to book table(ben)
PRIMARY KEY (ID),
FOREIGN KEY (BOOK_ID)
REFERENCES BOOK(ID)
);