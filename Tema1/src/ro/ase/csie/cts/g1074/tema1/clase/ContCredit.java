package ro.ase.csie.cts.g1074.tema1.clase;

import ro.ase.csie.cts.g1074.tema1.exceptii.ExceptieValoareNegativa;
import ro.ase.csie.cts.g1074.tema1.interfete.InterfataContabilitate;

public class ContCredit implements InterfataContabilitate {
	protected double valoareImprumut;
	protected double rata;	
	protected int perioadaRambursareInZile;
	public TipConturi tipCont;
	public final static double TAXA_BROKER=0.0125; 
	public final static int ZILE_AN=365;
	
	public ContCredit(double valoareImprumut, double rata, TipConturi tipCont) throws ExceptieValoareNegativa {
		if(valoareImprumut> 0) {
			this.valoareImprumut = valoareImprumut;
		}
		else {
			throw new ExceptieValoareNegativa("Valoarea imprumutului trebuie sa fie pozitiva");
		}
		this.rata = rata;
		this.tipCont = tipCont;
	}
	
	public void setValoareImprumut(double valoareImprumut) throws ExceptieValoareNegativa {
		if(valoareImprumut> 0) {
			this.valoareImprumut = valoareImprumut;
		}
		else {
			throw new ExceptieValoareNegativa("Valoarea imprumutului trebuie sa fie pozitiva");
		}
	}
	
	public double getValoareImprumut() {
		return this.valoareImprumut;
	}

	public double getRata() {
		return this.rata;
	}
	
	@Override
	public double getRataLunara() {
		return valoareImprumut*rata;
	}
	
	public static double calculeazaComisionTotal(ContCredit[] conturicredit) {
	double comisionTotal=0.0;
	for	(int i=0;i<conturicredit.length;i++) {
	if(conturicredit[i].tipCont==TipConturi.PREMIUM || conturicredit[i].tipCont==TipConturi.SUPER_PREMIUM) {
	comisionTotal+= TAXA_BROKER * conturicredit[i].valoareImprumut * 
	(Math.pow(conturicredit[i].rata,(conturicredit[i].perioadaRambursareInZile/ZILE_AN)) - conturicredit[i].valoareImprumut);	
	    }
	 }
	return	comisionTotal;
	}	
		
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Cont credit: ");
		stringBuilder.append("valoare imprumut= ");
		stringBuilder.append(this.valoareImprumut);
		stringBuilder.append("; rata= ");
		stringBuilder.append(this.rata);
		stringBuilder.append("; perioada rambursare= ");
		stringBuilder.append(this.perioadaRambursareInZile);
		stringBuilder.append(" zile");
		stringBuilder.append("; tip= ");
		stringBuilder.append(this.tipCont.toString());
		return stringBuilder.toString();
	}
}
