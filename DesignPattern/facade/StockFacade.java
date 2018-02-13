package facade;


class StockA{
	public void Buy() {
		System.out.println("股票A 买入1股");
	}
	public void Sell() {
		System.out.println("股票A 卖出1股");
	}
}

class StockB{
	public void Buy() {
		System.out.println("股票B 买入1股");
	}
	public void Sell() {
		System.out.println("股票B 卖出1股");
	}
}

class StockC{
	public void Buy() {
		System.out.println("股票C 买入1股");
	}
	public void Sell() {
		System.out.println("股票C 卖出1股");
	}
}

class NationalDebat{
	public void Buy() {
		System.out.println("国债 买入1股");
	}
	public void Sell() {
		System.out.println("国债 卖出1股");
	}
}

class Realty{
	public void Buy() {
		System.out.println("房地产 买入1股");
	}
	public void Sell() {
		System.out.println("房地产 卖出1股");
	}
}

/*
 * Fund类相当于股票类的Facade，由于炒股需要学习很多相关的知识，而且股民如果要炒股就需要
 * 去关注几十到几百支股票的动态，然而如果股民买基金(Fund)的话，不仅不需要学习很多专业知识，
 * 而且也不用花时间去关注成百上千之股票的动态，这就减轻了股民的负担。
 */
class Fund{
	private StockA stockA;
	private StockB stockB;
	private StockC stockC;
	private NationalDebat nd;
	private Realty realty;
	
	public Fund() {
		stockA = new StockA();
		stockB = new StockB();
		stockC = new StockC();
		nd = new NationalDebat();
		realty = new Realty();
	}
	
	public void Buy() {
		stockA.Buy();
		stockB.Buy();
		stockC.Buy();
		nd.Buy();
		realty.Buy();
	}
	
	public void Sell() {
		stockA.Sell();
		stockB.Sell();
		stockC.Sell();
		nd.Sell();
		realty.Sell();
	}
}


public class StockFacade {
	public static void main(String[] args) {
		Fund fund = new Fund();
		fund.Buy();
//		股票A 买入1股
//		股票B 买入1股
//		股票C 买入1股
//		国债 买入1股
//		房地产 买入1股
		System.out.println();
		fund.Sell();
//		股票A 卖出1股
//		股票B 卖出1股
//		股票C 卖出1股
//		国债 卖出1股
//		房地产 卖出1股
	}
}
