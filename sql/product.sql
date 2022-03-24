drop table product;
create table product (
    product_id   number(10),
    pname        varchar2(30),
    quantity     number(10),
    price        number(10)
);
--기본키생성
alter table product add Constraint product_product_id_pk primary key (product_id);

--시퀀스
drop sequence product_product_id_seq;
create sequence product_product_id_seq;

--생성--
insert into product (product_id,pname,quantity,price)
values (product_product_id_seq.nextval, '컴퓨터','1','1000000');

insert into product (product_id,pname,quantity,price)
values (product_product_id_seq.nextval, '모니터','2','500000');

insert into product (product_id,pname,quantity,price)
values (product_product_id_seq.nextval, '프린터','1','100000');

--조회
select product_id,pname,quantity,price
  from product
 where product_id = 1;

--수정
update product
  set pname = '컴퓨터2',
      quantity = 2,
      price = 2000000;

 --삭제
delete from product
 where product_id = 1;

--전체조회
select product_id,pname,quantity,price from product;

commit;
