package personal.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println(".-------------------COMMAND MENU------------------.");
        System.out.println("|$*#*&*$*#*&*$*#*&*$*#*&*$*#*&*$*#*&*$*#*&*$*#*&*$|");
        System.out.println(".-------------------------------------------------.");
        System.out.println("	1. 새로운 항목 추가 ( 명령어: add )");
        System.out.println("	2. 하나의 항목 삭제 ( 명령어: del )");
        System.out.println("	3. 하나의 항목 수정 ( 명령어: edit )");
        System.out.println("	4. 저장된 모든 항목 출력 ( 명령어: ls )");
        System.out.println("	5. 프로그램 종료 ( ESC key OR 명령어: exit )");
        System.out.println("	6. 여러 항목 삭제 ( 명령어: mt_del )");
        System.out.println("	7. 완료 체크하기  ( 명령어: comp )");
        System.out.println("	8. 여러 항목 완료 체크 ( 명령어: mt_comp )");
        System.out.println("	9. 완료된 항목만 출력하기 ( 명령어: ls_comp )");
        System.out.println("	10. 달력 일정 보기 ( 명령어: cal )");
        System.out.println(".-------------------------------------------------.");
        System.out.println();
    }

	public static void prompt() {
		System.out.print(">>> 명령어를 입력해주세요 (메뉴를 보시려면 \'help\'를 입력해주세요) : ");
		
	}
}
