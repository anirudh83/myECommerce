INSERT INTO pilllr_error VALUES (1, 'Amount is not valid', '10001', '0B', 'Invalid Amount','The transaction amount was not valid,Check data and try again');
INSERT INTO pilllr_error VALUES (2, 'Invalid decimal placement in Amount', '10002', '0C','Invalid Decimal Placement in Amount','The transaction amount has an invalid format.Check data and try again.');
INSERT INTO pilllr_error VALUES (3, 'Amount is not supplied', '10003', '0A', 'Blank Amount','The transaction amount was not set.Check data and try again.');
INSERT INTO pilllr_error VALUES (4, 'Invalid req type (Method un-identified)','10004','B1','Invalid Method','An invalid request was received.  Check data and try again.');
INSERT INTO pilllr_error VALUES (5, 'Encryption Error', '10005', '0R', 'ENCRYPTION ERROR','There has been an encryption error.Contact Bank Helpdesk.');
INSERT INTO pilllr_error VALUES (6, 'Format Error', '10006', '0S', 'format error','Incorrect data format.Check data and try again.');
INSERT INTO pilllr_error VALUES (7, 'Link Error', '10007', 'A4', 'link failure','The communications to the acquirer is down.Contact Bank Helpdesk.');
INSERT INTO pilllr_error VALUES (8, 'Execute Operation Success', '10015', 'P00','Execute Success','Execute Operation was succesful');
INSERT INTO pilllr_error VALUES (9, 'Card Query Failed', '10016', 'P01', 'Execute Failed - Card Query Failed','Transaction failed, due to Card Query failure, please try again');
INSERT INTO pilllr_error VALUES (10, 'Pre Auth Failed','10017','P02','Execute Failed - Pre Auth Failed','Transaction failed, due to Pre Auth Failure, please try again.');
INSERT INTO pilllr_error VALUES (11, 'Settlement Failed', '10018', 'P03', 'Execute Failed - Settlement Failed','Transaction failed, due to Settlement Failure, please try again.');
INSERT INTO pilllr_error VALUES (12, 'QPFS Request Validation Failed', '10019', 'P04', 'Execute Failed - Request Validation Failure','An error was observed in Execute request, please check data and try again.');
INSERT INTO pilllr_error VALUES (13, 'Fraud Screening Rejected', '10020', 'P05', 'Execute Failed - Fraud Screening Rejected','Transaction failed, due to Fraud Screening Rejection');
INSERT INTO pilllr_error VALUES (14, 'Authentication Failure', '10021', 'W1', 'Pilllr Authentication with payment gateway failed','Pilllr Authentication with payment gateway failed');
INSERT INTO pilllr_error VALUES (15, 'Invalid Request', '10022', 'W2', 'API call failed. Please check the values and try again','API call failed. Please check the values and try again');
INSERT INTO pilllr_error VALUES (16, 'Incorrect Value', '10023', '0V', 'Incorrect values in the request','Incorrect values in the request. Please check the values and try again');
INSERT INTO pilllr_error VALUES (17, 'Invalid Pilllr Merchant ID', '10108', 'P1', 'Pilllr Merchant Identification does not correspond to an active client in the database.  Check data and try again.','Pilllr Merchant Identification does not correspond to an active client in the database.  Check data and try again.');
INSERT INTO pilllr_error VALUES (18, 'CUST CONTACT THEIR BANK', '10024', '01', 'CUST CONTACT THEIR BANK','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (19, 'CUST CONTACT THEIR BANK', '10025', '03', 'CUST CONTACT THEIR BANK','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (20, 'CUST CONTACT THEIR BANK', '10026', '04', 'CUST CONTACT THEIR BANK','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (21, 'CUST CONTACT THEIR BANK', '10027', '05', 'CUST CONTACT THEIR BANK','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (22, 'CUST CONTACT THEIR BANK', '10028', '06', 'CUST CONTACT THEIR BANK','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (23, 'INVALID TRANSACTION', '10029', '12', 'INVALID TRANSACTION','Seems there was a problem, Please contact your bank');
INSERT INTO pilllr_error VALUES (24, 'INVALID AMOUNT', '10030', '13', 'INVALID AMOUNT','Seems there was a problem with the amount, Please check the details and try again');
INSERT INTO pilllr_error VALUES (25, 'CARD NO INVALID', '10031', '14', 'CARD NO INVALID','Seems there was a problem with your card number, Please check the details and try again');
INSERT INTO pilllr_error VALUES (26, 'DECLINED SYSTEM ERROR', '10032', '15', 'DECLINED SYSTEM ERROR','Transaction Declined due to system error');
INSERT INTO pilllr_error VALUES (27, 'CONTACT BANK', '10033', '41', 'CONTACT BANK','Customer should contact their bank / Call Centre');
INSERT INTO pilllr_error VALUES (28, 'Invalid Account', '10034', '42', 'Invalid Account','Customer should contact their bank / Call Centre');
INSERT INTO pilllr_error VALUES (29, 'CONTACT BANK', '10035', '43', 'CONTACT BANK','Customer should contact their bank / Call Centre');
INSERT INTO pilllr_error VALUES (30, 'INSUFFICIENT FUNDS', '10036', '51', 'INSUFFICIENT FUNDS','INSUFFICIENT FUNDS, Please contact your bank');
INSERT INTO pilllr_error VALUES (31, 'EXPIRED CARD', '10037', '54', 'EXPIRED CARD','EXPIRED CARD, Please contact your bank');
INSERT INTO pilllr_error VALUES (32, 'DECLINED SYSTEM ERROR', '10038', '55', 'DECLINED SYSTEM ERROR','DECLINED SYSTEM ERROR, Please contact your bank');
INSERT INTO pilllr_error VALUES (33, 'INVALID TRANSACTION', '10039', '57', 'INVALID TRANSACTION','INVALID TRANSACTION, Please contact your bank');
INSERT INTO pilllr_error VALUES (34, 'TRANSACTION NOT ALLOWED', '10040', '58', 'TRANSACTION NOT ALLOWED','TRANSACTION NOT ALLOWED, Please contact your bank');
INSERT INTO pilllr_error VALUES (35, 'DAILY AMOUNT EXCEEDED', '10041', '61', 'DAILY AMOUNT EXCEEDED','DAILY AMOUNT EXCEEDED, Please contact your bank');
INSERT INTO pilllr_error VALUES (36, 'TRANSACTION NOT ALLOWED', '10042', '62', 'TRANSACTION NOT ALLOWED','TRANSACTION NOT ALLOWED, Please contact your bank');
INSERT INTO pilllr_error VALUES (37, 'DAILY TRANSACTION EXCEEDED', '10043', '65','DAILY TRANSACTION EXCEEDED','DAILY TRANSACTION EXCEEDED, Please contact your bank');
INSERT INTO pilllr_error VALUES (38, 'DECLINED SYSTEM ERROR', '10044', '75', 'DECLINED SYSTEM ERROR','DECLINED SYSTEM ERROR, Please contact your bank');
INSERT INTO pilllr_error VALUES (39, 'BANK NOT AVAILABLE', '10045', '91', 'BANK NOT AVAILABLE ','BANK NOT AVAILABLE , Please contact your bank');
INSERT INTO pilllr_error VALUES (40, 'DECLINED SYSTEM ERROR', '10046', '92', 'DECLINED SYSTEM ERROR','DECLINED SYSTEM ERROR, Please contact your bank');
INSERT INTO pilllr_error VALUES (41, 'DECLINED SYSTEM ERROR', '10047', '99', 'DECLINED SYSTEM ERROR','DECLINED SYSTEM ERROR, Please contact your bank');
INSERT INTO pilllr_error VALUES (42, 'INVALID COMPLETION', '10048', '0T', 'INVALID COMPLETION','INVALID COMPLETION, Please contact your bank');
INSERT INTO pilllr_error VALUES (43, 'CARD DATA SPECIFIED BUT NOT EXPECTED', '10049', 'A9', 'CARD DATA SPECIFIED BUT NOT EXPECTED','CARD DATA SPECIFIED BUT NOT EXPECTED, Check data and try again.');
INSERT INTO pilllr_error VALUES (44, 'NO CONNECTION ERROR', '10050', 'AG', 'NO CONNECTION ERROR','NO CONNECTION ERROR, Check data and try again');
INSERT INTO pilllr_error VALUES (45, 'VOID APPROVED', '10051', 'CA', 'VOID APPROVED','VOID APPROVED, Please contact Help Desk');
INSERT INTO pilllr_error VALUES (46, 'INITIALISED', '10052', 'IN', 'INITIALISED','INITIALISED');
INSERT INTO pilllr_error VALUES (47, 'IN PROGRESS', '10053', 'IP', 'IN PROGRESS','IN PROGRESS');
INSERT INTO pilllr_error VALUES (48, 'CARD UNSUPPORTED', '10054', 'T1', 'CARD UNSUPPORTED','CARD UNSUPPORTED, Please try again');
INSERT INTO pilllr_error VALUES (49, 'INVALID VOID', '10055', 'VF', 'INVALID VOID','INVALID VOID, Please contact help desk');
INSERT INTO pilllr_error VALUES (50, 'SYSTEM ERROR ', '10054', 'W7', 'SYSTEM ERROR','SYSTEM ERROR, Please try again later');
INSERT INTO pilllr_error VALUES (51, 'HOST NOT AVAILABLE', '10057', 'W8', 'HOST NOT AVAILABLE ','HOST NOT AVAILABLE , Please try again later');
INSERT INTO pilllr_error VALUES (52, 'UNKNOWN ERROR', '10058', 'W9', 'UNKNOWN ERROR','UNKNOWN ERROR, Please try again later');
INSERT INTO pilllr_error VALUES (53, 'UNABLE TO PROCESS', '10059', 'Y3', 'UNABLE TO PROCESS','UNABLE TO PROCESS, Please try again later');
INSERT INTO pilllr_error VALUES (54, 'UNABLE TO PROCESS', '10060', '-1', 'UNABLE TO PROCESS','UNABLE TO PROCESS, Please try again later');
INSERT INTO pilllr_error VALUES (55, 'Transaction Reference Not Supplied', '10061', '0N', 'Transaction Reference Not Supplied','For a status check, the transaction reference was not supplied.  Check data and try again.');











