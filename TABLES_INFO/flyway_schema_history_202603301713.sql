INSERT INTO public.flyway_schema_history (installed_rank,"version",description,"type",script,checksum,installed_by,installed_on,execution_time,success) VALUES
	 (1,'001','create-administrators-table','SQL','V001__create-administrators-table.sql',-24558542,'postgres','2025-07-17 10:07:55.762579',21,true),
	 (2,'002','create-scholarshipholders-table','SQL','V002__create-scholarshipholders-table.sql',1426643393,'postgres','2025-07-17 10:07:55.966581',9,true),
	 (3,'003','create-customers-table','SQL','V003__create-customers-table.sql',2049950501,'postgres','2025-07-17 10:07:55.990832',16,true),
	 (4,'004','create-courses-table','SQL','V004__create-courses-table.sql',157296346,'postgres','2025-07-17 10:07:56.016702',9,true),
	 (5,'005','create-registrations-table','SQL','V005__create-registrations-table.sql',-1923964372,'postgres','2025-07-17 10:07:56.034273',12,true),
	 (6,'006','create-institutions-table','SQL','V006__create-institutions-table.sql',737671504,'postgres','2025-07-17 10:07:56.056374',32,true),
	 (7,'007','add-foreign-keys','SQL','V007__add-foreign-keys.sql',-1446428112,'postgres','2025-07-17 10:07:56.095855',26,true),
	 (8,'008','create-roles-table','SQL','V008__create-roles-table.sql',358753105,'postgres','2025-07-17 10:07:56.128492',19,true),
	 (9,'009','alter table-institutions-customers','SQL','V009__alter_table-institutions-customers.sql',-1996258444,'postgres','2025-07-17 10:07:56.155709',27,true),
	 (10,'010','create companies table','SQL','V010__create_companies_table.sql',1203714120,'postgres','2025-07-17 10:07:56.199276',17,true);
INSERT INTO public.flyway_schema_history (installed_rank,"version",description,"type",script,checksum,installed_by,installed_on,execution_time,success) VALUES
	 (11,'011','create status registrations','SQL','V011__create_status_registrations.sql',845393986,'postgres','2025-07-17 10:07:56.223132',33,true),
	 (12,'012','change-costumers','SQL','V012__change-costumers.sql',-59376126,'postgres','2025-08-11 17:12:01.373527',62,true),
	 (13,'013','alter table-cursos ano bolsa','SQL','V013__alter_table-cursos_ano_bolsa.sql',1407137941,'postgres','2025-08-19 15:46:37.441057',56,true),
	 (14,'014','create password reset tokens','SQL','V014__create_password_reset_tokens.sql',-1578298177,'postgres','2025-08-19 15:46:37.691454',11,true),
	 (15,'015','modify-companies','SQL','V015__modify-companies.sql',399639230,'postgres','2025-08-19 15:46:37.712588',10,true),
	 (16,'016','create-transactions-table','SQL','V016__create-transactions-table.sql',-2111900550,'postgres','2025-08-19 15:46:37.733627',47,true);
