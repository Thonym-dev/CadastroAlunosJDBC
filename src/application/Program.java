package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {
		
		AlunoJDBC acao = new AlunoJDBC();
		
		try {
        	
            int opcao = 0;
            @SuppressWarnings("resource")
			Scanner console = new Scanner(System.in);
            
            do {
            	System.out.println("####### Menu #######"
            						+ "\n1 - Cadastrar"
            						+ "\n2 - Listar"
            						+ "\n3 - Alterar"
            						+ "\n4 - Excluir"
            						+ "\n5 - Sair");
            	System.out.println("\n\tOpção:");
            	opcao = Integer.parseInt(console.nextLine());
            	
            	if (opcao == 1) {
            	
            		Aluno a = new Aluno();
            		
            		
            		System.out.println("\n ### Cadastrar Aluno ### \n\r");
            		
            		System.out.print("Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Data de Nascimento (dd-mm-aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		acao.salvar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}
            	if (opcao == 2) {
            		
            		List<Aluno> lista = acao.listar();
            		for (Aluno i : lista) {
            			System.out.println(
            					i.getId() + " " +
            					i.getNome() + " " +
            					i.getSexo()+ " "+
            					i.getDt_nasc()) ;
            		}
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            		
            	}
            	if (opcao == 3) {
            		Aluno a = new Aluno();
            		System.out.println("\n ### Alterar Aluno ### \n\r");
            		
            		System.out.print("ID: ");
            		a.setId(Integer.parseInt(console.nextLine()));
            		
            		System.out.print("Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Data de Nascimento (dd-mm-aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		acao.alterar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}	
            	if (opcao == 4) {
            		System.out.println("ID do Aluno: ");
            		Aluno a = new Aluno();
            		a.setId(Integer.parseInt(console.nextLine()));
            		acao.apagar(a.getId());
            		
            		
            	}
            } while(opcao != 5);
            
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	} 
}