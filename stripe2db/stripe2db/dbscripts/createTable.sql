CREATE TABLE StripeBalanceTransaction (
    id nvarchar(100) PRIMARY KEY,
    objectName nvarchar(100),
    amount int,
	available_on nvarchar(100),
	created nvarchar(100),
	currency nvarchar(100),
	TransactionDescription nvarchar(100),
	exchange_rate nvarchar(100),
	fee int,
    net int,
	reporting_category nvarchar(100),
	TransactionSource nvarchar(100),
	Transactionstatus nvarchar(100),
	Transactiontype nvarchar(100)
);