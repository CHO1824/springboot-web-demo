-- 1. 날짜 조회용 인덱스 생성
CREATE INDEX IF NOT EXISTS idx_product_created_at ON product (created_at DESC);

-- 2. 상품명 중복 방지 제약조건 생성
--ALTER TABLE product ADD UNIQUE (name);