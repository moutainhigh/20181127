-- 数据库:zxdb
-- 用户:YMFAPP

-- 增加字段 ACCOUNT_ID
ALTER TABLE YMF.TBL_USER ADD COLUMN ACCOUNT_ID VARCHAR(50);

-- 更新ACCOUNT_ID
UPDATE YMF.TBL_USER A
SET A.ACCOUNT_ID = (SELECT B.ID FROM YMF.TBL_ACCOUNT_OPEN B WHERE B.MEMBER_NO = A.MEMBER_NO);

-- 增加字段长度
ALTER TABLE YMF.TBL_ACCOUNT_OPEN ALTER OP_REG_NO SET DATA TYPE VARCHAR(128);
















