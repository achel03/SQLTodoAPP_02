package personal.todo.service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import personal.todo.dao.TodoItem;
import personal.todo.dao.TodoList;

public class TodoUtil {

	public static void addItem(TodoList l) {
		String title, writer, memo, cate, due;
		int star, comp;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"+"제목 > ");
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		System.out.print("작성자 > ");
		writer = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		memo = sc.nextLine().trim();
		System.out.print("카테고리 > ");
		cate = sc.next();
		sc.nextLine();
		System.out.print("기한 > ");
		due = sc.nextLine().trim();
		System.out.print("완료 여부(1: 완료, 0: 미완료) > ");
		comp = sc.nextInt();
		System.out.print("중요 > ");
		star = sc.nextInt();
		
		TodoItem t = new TodoItem(title, writer, memo, cate, due, comp, star);
		if(l.addItemL(t)>0)
			System.out.println("추가되었습니다:)");
		
	}

	public static void editItem(TodoList l) {
		String new_title, new_writer, new_memo, new_cate, new_due;
		int new_star, new_comp;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"+"수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		System.out.print("새 작성자 > ");
		new_writer = sc.next().trim();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_memo = sc.nextLine().trim();
		System.out.print("새 카테고리 > ");
		new_cate = sc.next().trim();
		sc.nextLine();
		System.out.print("새 마감기한 > ");
		new_due = sc.nextLine().trim();
		System.out.print("완료 여부(1: 완료, 0: 미완료) > ");
		new_comp = sc.nextInt();
		System.out.print("새 중요도 > ");
		new_star = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title,  new_writer, new_memo, new_cate, new_due, new_comp, new_star);
		t.setId(index);
		if(l.editItemL(t)>0)
			System.out.println("수정되었습니다:)");
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem it : l.getList()) {
			System.out.println(it.toString(it));
		}
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 삭제]\n"+"삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}

	public static void compItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 체크]\n"+"완료 체크 할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.checkItem(index)>0)
			System.out.println("체크 표시되었습니다.");
		
	}

	public static void listComp(TodoList l) {
		System.out.printf("[완료된 항목 %d개 / 총 %d개]\n", l.getCountComp(),l.getCount());
		for(TodoItem it : l.getListComp()) {
			System.out.println(it.toString(it));
		}
		
	}

	public static void MultideleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 삭제]\n"+"삭제할 항목의 번호들을 입력하시오 > ");
		String[] arr = sc.nextLine().split(" ");
		if(l.MtdeleteItem(arr)>0)
			System.out.println("삭제되었습니다.");
		
	}

	public static void MultiComp(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 체크]\n"+"완료 체크 할 항목의 번호들을 입력하시오 > ");
		String[] arr = sc.nextLine().split(" ");
		if(l.MtCompItem(arr)>0)
			System.out.println("체크 표시되었습니다.");
		
	}

	public static void checkCalendar() {
			Scanner sc = new Scanner(System.in);
			System.out.println("연도를 입력하세요 > ");
			int year = sc.nextInt();
			System.out.println("월을 입력하세요 > ");
			int month = sc.nextInt();

			if (year < 1 || month > 12 || month < 1) {
				System.out.println("잘못 입력하셨습니다.");
				year = sc.nextInt();
				month = sc.nextInt();
			}

			Calendar.printCalendar(year, month);

		}
		
	}
	

