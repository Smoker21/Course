-- 開一個 asset_type 資料表
CREATE TABLE if not exists Asset_Type 
     (Asset_Type varchar(40) NOT NULL, 
      Type_Description varchar(2000), 
      update_user varchar(50), 
      update_dt timestamp, 
      PRIMARY KEY (Asset_Type));
-- 做一個 sequence
CREATE SEQUENCE if not exists UNI_SEQ; 
-- Insert 三筆資料
INSERT INTO ASSET_TYPE(ASSET_TYPE,TYPE_DESCRIPTION,UPDATE_USER,UPDATE_DT) 
     VALUES ('PC','Personal computer','TEST',sysdate);
INSERT INTO ASSET_TYPE(ASSET_TYPE,TYPE_DESCRIPTION,UPDATE_USER,UPDATE_DT) 
     VALUES ('NoteBook','Laptop computer','TEST',sysdate);
INSERT INTO ASSET_TYPE(ASSET_TYPE,TYPE_DESCRIPTION,UPDATE_USER,UPDATE_DT) 
     VALUES ('Thin Client','Thin client with backend host','TEST',sysdate);
