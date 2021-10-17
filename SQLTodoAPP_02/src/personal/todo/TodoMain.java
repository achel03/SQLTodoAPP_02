package personal.todo;

import java.util.Scanner;

import personal.todo.dao.TodoList;
import personal.todo.menu.Menu;
import personal.todo.service.TodoUtil;


public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("pj_todolist.txt");
		boolean quit = false;
		do {
			Menu.prompt();
			String choice = sc.nextLine();
			switch (choice) {
			
			case "help":
				Menu.displaymenu();
				break;
			case "add":
				TodoUtil.addItem(l);
				break;
			case "edit":
				TodoUtil.editItem(l);
				break;
			case "ls":
				TodoUtil.listAll(l);
				break;
			case "del":
				TodoUtil.deleteItem(l);
				break;
			case "mt_del":
				TodoUtil.MultideleteItem(l);
				break;
			case "comp":
				TodoUtil.compItem(l);
				break;
			case "ls_comp":
				TodoUtil.listComp(l);
				break;
			case "mt_comp":
				TodoUtil.MultiComp(l);
				break;
			}
			
		} while (!quit);
	sc.close();
	}
}
