package com.vivek.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vivek.model.CitizenApplicationModel;
import com.vivek.model.CoTriggers;
import com.vivek.model.DcCasesModel;
import com.vivek.model.EligDtls;
import com.vivek.repo.CitizenApplicationRepo;
import com.vivek.repo.CoTriggerRepo;
import com.vivek.repo.DcCasesRepo;
import com.vivek.repo.EligDtlsRepo;
import com.vivek.util.SendMail;

@Service
public class CoTrgServiceImpl implements CoTrgService {

	@Autowired
	private EligDtlsRepo eligRepo;

	@Autowired
	private CoTriggerRepo trgRepo;

	@Autowired
	private DcCasesRepo dcCasesRepo;

	@Autowired
	private CitizenApplicationRepo citizenRepo;

	@Override
	public CoTriggers correspondenceTrg(Long caseNum) throws Exception {
		Integer appId = null;
		CoTriggers coTriggers = trgRepo.findByCaseNum(caseNum);
		String trgStatus = coTriggers.getTrgStatus();
		if ("Pending".equals(trgStatus)) {
			Optional<DcCasesModel> findById = dcCasesRepo.findById(caseNum);
			if (findById.isPresent()) {
				DcCasesModel dcCasesModel = findById.get();
				appId = dcCasesModel.getAppId();
			}

			generatePdf(caseNum, appId);
			EligDtls eligDtls = eligRepo.findByCaseNum(caseNum);

			String filePath = "E:\\21-JRTP 25 JULY-2022\\13-Major_Project-Correspondence-Module\\src\\main\\resources\\reports\\"
					+ eligDtls.getHolderName() + ".pdf";
			/*
			 * Path path = Paths.get(filePath); byte[] buffer =
			 * java.nio.file.Files.readAllBytes(path);
			 */
			InputStream initialStream = new FileInputStream(new File(filePath));
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);
			coTriggers.setCoPdf(buffer);
			coTriggers.setTrgStatus("send");
			trgRepo.save(coTriggers);

			initialStream.close();

		}
		return coTriggers;
	}

	private void generatePdf(Long caseNum, Integer appId) throws DocumentException, FileNotFoundException {
		EligDtls eligDtls = eligRepo.findByCaseNum(caseNum);
		String filePath = "E:\\21-JRTP 25 JULY-2022\\13-Major_Project-Correspondence-Module\\src\\main\\resources\\reports\\"
				+ eligDtls.getHolderName() + ".pdf";

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, new FileOutputStream(filePath));

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Eligibility-Status-Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 4.0f, 3.5f, 3.0f, 3.5f, 3.0f, 3.0f, 4.0f, });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(7);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Holder Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Benifit Ammount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Denial Reason", font));
		table.addCell(cell);
		String denialReason = eligDtls.getDenialReason();

		table.addCell(eligDtls.getHolderName());
		table.addCell(eligDtls.getPlanName());
		table.addCell(eligDtls.getPlanStatus());
		table.addCell(String.valueOf(eligDtls.getPlanStartDate()));
		table.addCell(String.valueOf(eligDtls.getPlanEndDate()));
		table.addCell(String.valueOf(eligDtls.getBenefitAmount()));
		table.addCell(eligDtls.getDenialReason());
		document.add(table);

		Optional<CitizenApplicationModel> citizenApp = citizenRepo.findById(appId);
		if (citizenApp.isPresent()) {

			CitizenApplicationModel citizenApplicationModel = citizenApp.get();
			SendMail.sendAttach("Your Eligibility Report", "Status Report", citizenApplicationModel.getEmail(),
					"chitrakoothospital108@gmail.com", filePath);

		}

		document.close();
	}

}
