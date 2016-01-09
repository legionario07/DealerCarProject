package br.com.dealercar.bean;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBFile")
public class FileUploadView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void upload(FileUploadEvent event){
		
		file = event.getFile();
		
		System.out.println("Entrou no upload");
		
		if(file != null){
			JSFUtil.adicionarMensagemSucesso("Imagem Carregada om Sucesso");
			
			StringBuffer caminho = new StringBuffer();
			caminho.append("/");
			caminho.append(file.getFileName());
			
			System.out.println("file.getFileName()" + file.getFileName());
			System.out.println("file.getFileName()" + file.getSize());
			
			
		}else{
			System.out.println("Se entrou aki eh nulo");
		}
		
	}
	
	
	
}
