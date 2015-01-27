package pl.edu.wroc.pwr.service.manager.service.utils;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.edu.wroc.pwr.service.EnvironmentVariables;

import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by krzaczek on 04.12.14.
 */
public class PictureConverter {

	public static final String ROOT_WAR_IMAGES = "standalone/deployments/ROOT.war/images/";
	public static final String UPLOADED_FILE_PATH = EnvironmentVariables.DEPLOYMENTS_PATH + ROOT_WAR_IMAGES;

	public String convertAndSave(MultipartFormDataInput input) {

		Long timeStamp = new Date().getTime();

		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				//convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				//constructs upload file path
				fileName = timeStamp + fileName;

				writeFile(bytes, UPLOADED_FILE_PATH+ fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return fileName;
	}

	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}

	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
}

