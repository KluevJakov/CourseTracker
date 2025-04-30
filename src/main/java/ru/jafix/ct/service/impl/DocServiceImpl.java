package ru.jafix.ct.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.repository.UserRepository;
import ru.jafix.ct.service.DocService;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void test() {
        List<User> studentList = userRepository.findByRoleUser();

        try (XWPFDocument document = new XWPFDocument()) {
            createParagraph(document, "Список студентов");

            XWPFTable table = document.createTable(studentList.size() + 1, 3);

            table.getRow(0).getCell(0).setText("UUID");
            table.getRow(0).getCell(1).setText("Email");
            table.getRow(0).getCell(2).setText("Возраст");

            for (int i = 1; i < studentList.size()+1; i++) {
                table.getRow(i).getCell(0).setText(studentList.get(i-1).getId().toString());
                table.getRow(i).getCell(1).setText(studentList.get(i-1).getEmail());
                table.getRow(i).getCell(2).setText(String.valueOf(studentList.get(i-1).getAge()));
            }

            createParagraph(document, "___________________");

            try (OutputStream os = new FileOutputStream("/Users/aptech/IdeaProjects/coursetracker/src/main/resources/TestDocx1.docx")) {
                document.write(os);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void createParagraph(XWPFDocument document, String text) {
        XWPFParagraph p = document.createParagraph();
        XWPFRun r = p.createRun();
        r.setText(text);
    }
}
