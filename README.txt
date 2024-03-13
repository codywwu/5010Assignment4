README-Features:
	Our implementation supports the following features: 

	
*User will be able to create portfolios add stocks into the portfolio, once created, user will no longer be able to manage the portfolio in any kind.
*User would be able to prompt to enter a opening market date to check out one single share of a company symbol, total shares of user on one company symbol, and finally the total value of the portfolio.
*Import a portfolio from pre-created XML file, if the XML file information is invalid such as the company symbol is not exist, the user would not be able to import the portfolio into the portfolio list.
*Read and store multiple user names, and their corresponding portfolios and it’s names, stock information, and corresponding shares the user purchased(User will be prohibited from buying 0, negative shares and partial shares, for example, 0.5 shares of GOOG). All the information will be stored in data.xml file.
*A user would only be able to access and modify their own saved information. 
*Live updated XML data for user, no need to existing the program to stored information. 
*Since the limitation times of access Alphabet Vintage API, we are storing first-time accessed company information as a XML file into the local machine, the information will be but not limited to : company stock symbol, open value, closed value and so on, when the next time if there is a user or a new user trying to access the stored XML file, the stored XML file will be accessed instead of the API to reduce the API accessed time

