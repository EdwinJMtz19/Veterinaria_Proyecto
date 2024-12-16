/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author 52951
 */
public class Utils {
     private String mensajeHTML(String usuario, String fecha, String hora){
        return  "<h1 style='color: #0047AB;'>Concertado de cita para Veterinaria</h1>"
                    + "<p>Estimado/a usuario/a "+usuario+" </p>"
                    + "<p>Hola </p>"
                    +"<p> Esto es para recordarle sobre el establecimiento de cita el "+fecha+" a las "+hora+" horas<p>"
                    + "<p>¡Gracias por su colaboración!</p>"
                    + "<p>Atentamente,<br/>" 
                    + "Veterinaria Vital Vet</p>"
                    + "© 2024 Veterinaria</p>"
                    + "<hr/>"
                    + "<p style='font-size: 10px; color: #666;'>© " + Calendar.getInstance().get(Calendar.YEAR) + " Veterinaria.</p>";
    }
    
    public void sendEmail(String subject, String email,String fecha, String hora) throws IOException, Exception {
        
        
        
        // Configuración de las propiedades para la conexión con el servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Cambia "smtp.example.com" por tu servidor SMTP
        props.put("mail.smtp.port", "587"); // Puerto SMTP
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Credenciales de autenticación
        String username = "veterinariavitalvet75@gmail.com"; // Cambia por tu dirección de correo electrónico
        String password = "tsce ssor nnse gelp"; // Cambia por tu contraseña

        // Creación de la sesión
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            emailMessage.setSubject(subject);
            String htmlContent = mensajeHTML(subject, fecha, hora); 

            // Adjuntar el archivo PDF al mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            
            

            // Crear el multipart para el cuerpo del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            emailMessage.setContent(multipart);

            // Envío del mensaje
            Transport.send(emailMessage);
            
            
        JOptionPane.showMessageDialog(null, "Correo electrónico enviado correctamente.");


        } catch (AuthenticationFailedException ex) {
            JOptionPane.showMessageDialog(null, "Error de autenticación al enviar el correo electrónico: " + ex.getMessage());
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo electrónico: " + ex.getMessage());
        }
    }
    
    
    
    
     private void crearPdf(String mascota, String dueño,  String [] recomendaciones) {
        // Crear un nuevo documento PDF
        PDDocument document = new PDDocument();

        try {
            // Añadir una página al documento
            PDPage page = new PDPage();
            document.addPage(page);

            String[] rules = recomendaciones;

            // Iniciar una nueva secuencia de contenido
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Dibujar los márgenes
            contentStream.setLineWidth(2);
            contentStream.setStrokingColor(0, 0, 0); // Color negro
            contentStream.moveTo(40, 40);
            contentStream.lineTo(40, 750);
            contentStream.lineTo(550, 750);
            contentStream.lineTo(550, 40);
            contentStream.closeAndStroke();

            // Dibujar encabezado
            contentStream.setStrokingColor(100, 100, 100); // Color gris claro
            contentStream.moveTo(40, 740);
            contentStream.lineTo(550, 740);
            contentStream.closeAndStroke();

            // Dibujar pie de página
            contentStream.moveTo(40, 50);
            contentStream.lineTo(550, 50);
            contentStream.closeAndStroke();

            // Escribir fecha en el encabezado
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());

            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(450, 755);
            contentStream.showText("Fecha: " + date);
            contentStream.endText();

           

            // Escribir el título
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
            contentStream.beginText();
            contentStream.newLineAtOffset(120, 720); // ajustar la posición para dejar espacio para el logo
            contentStream.showText("Veterinaria vet");
            contentStream.endText();

            // Escribir subtítulo
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(120, 690);
            contentStream.showText("Receta Medica");
            contentStream.endText();

           
            // Escribir detalles del libro
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 640);
            contentStream.showText("Se ha generado una receta para la siguiente mascota");
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 620);
            contentStream.showText("Mascota: " + mascota);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 600);
            contentStream.showText("Dueño: " + dueño);
            contentStream.endText();

            // Dibujar línea separadora
            contentStream.setStrokingColor(200, 200, 200); // Color gris claro
            contentStream.moveTo(40, 500);
            contentStream.lineTo(550, 500);
            contentStream.closeAndStroke();

            // Escribir título de las reglas
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 560);
            contentStream.showText("Recomendaciones generales :> :");
            contentStream.endText();

            // Escribir las reglas
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            int yPosition = 540;
            for (String rule : rules) {
                contentStream.beginText();
                if (rule.charAt(0) == "©".charAt(0)) {
                    yPosition -= 80;
                }
                contentStream.newLineAtOffset(80, yPosition);
                contentStream.showText(rule);
                contentStream.endText();
                yPosition -= 20; // Ajusta el espaciado según sea necesario

                if (yPosition < 60) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = 750;

                    // Redibujar los márgenes
                    contentStream.setLineWidth(2);
                    contentStream.setStrokingColor(0, 0, 0); // Color negro
                    contentStream.moveTo(40, 40);
                    contentStream.lineTo(40, 750);
                    contentStream.lineTo(550, 750);
                    contentStream.lineTo(550, 40);
                    contentStream.closeAndStroke();

                }
            }

            // Cerrar la secuencia de contenido
            contentStream.close();

            // Guardar el documento en un archivo
            document.save("RecetaMedica.pdf");
            System.out.println("PDF creado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el documento
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendEmail(String subject, String email, String mascota, String duenio, String [] recomendaciones) throws IOException {
        // Configuración de las propiedades para la conexión con el servidor SMTP
        
        crearPdf( mascota, duenio, recomendaciones);
        
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Cambia "smtp.example.com" por tu servidor SMTP
        props.put("mail.smtp.port", "587"); // Puerto SMTP
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Credenciales de autenticación
        String username = "veterinariavitalvet75@gmail.com"; // Cambia por tu dirección de correo electrónico
        String password = "tsce ssor nnse gelp"; // Cambia por tu contraseña

        // Creación de la sesión
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            emailMessage.setSubject(subject);

            // Crear el cuerpo del mensaje en formato HTML
            String htmlContent = "<h1 style='color: #0047AB;'>Receta Medica Veterinaria Vet</h1>"
                    + "<p>Estimado/a usuario/a</p>"
                    + "<p>Adjunto encontrará la receta medica a contiunacion.</p>"
                    + "<p>¡Gracias por su preferencia!</p>"
                    + "<p>Atentamente,<br/>"
                    + "Veterinaria Vitalvet</p>"
                    + "© 2024 Veterinaria vet Todos los derechos reservados</p>"
                    + "<hr/>"
                    + "<p style='font-size: 10px; color: #666;'>© " + Calendar.getInstance().get(Calendar.YEAR) + " Veterinaria perrillos. Todos los derechos reservados.</p>";

            // Adjuntar el archivo PDF al mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            

            // Crear el multipart para el cuerpo del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Adjuntar el PDF
            MimeBodyPart attachPart = new MimeBodyPart();
            DataSource source = new FileDataSource("RecetaMedica.pdf");
            attachPart.setDataHandler(new DataHandler(source));
            attachPart.setFileName("RecetaMedica.pdf");
            multipart.addBodyPart(attachPart);

            // Establecer el contenido del mensaje con los archivos adjuntos
            emailMessage.setContent(multipart);

            // Envío del mensaje
            Transport.send(emailMessage);

            JOptionPane.showMessageDialog(null, "Envío de correo exitoso.");
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo electrónico: " + ex.getMessage());
        }

        String filePath = "RecetaMedica.pdf";

        // Crear un objeto File con la ruta del archivo
        File file = new File(filePath);

        // Verificar si el archivo existe antes de intentar eliminarlo
        if (file.exists()) {
            // Intentar eliminar el archivo
            if (file.delete()) {
                System.out.println("El archivo se eliminó correctamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");

        }

    }
}
