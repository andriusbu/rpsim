package lt.bumbis.rpsim.reports;

import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class AbstractJasperReport {
	
	public static void main(String[] args) {
		AbstractJasperReport report = new AbstractJasperReport();
		report.show();
	}
	
	public void show() {
		JasperReport jReport;
		JasperPrint jPring;
		
		try {
			jReport = JasperCompileManager.compileReport("src/main/jasperreports/Report1.jrxml");
			jPring = JasperFillManager.fillReport(jReport, new HashMap(), new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jPring, "Report.pdf");
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
	}

}
