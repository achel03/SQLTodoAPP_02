package personal.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.common.base.Strings;

public class TodoItem {
    private String title;
    private String writer;
    private String memo;
    private String current_date; // static 했을 때 저장 문제
    private String category;
    private String due_date;
    private int is_completed;
    private int id;
    private int importance;

    public TodoItem(String title, String writer, String memo, String cate, String due, int comp, int star) {
    	this.category=cate;
    	this.title=title;
    	this.writer=writer;
    	this.memo=memo;
    	SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	this.current_date=f.format(new Date());
    	this.due_date=due;
    	this.is_completed = comp;
    	this.importance = star;
    }
	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}


	public String getDue_date() {
		return due_date;
	}
	
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String desc) {
    	this.memo = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
    	this.current_date = current_date;
    }
    public String toSaveString() {
    	return category+"##"+title+"##"+writer+"##"+memo+"##"+current_date+"##"+due_date+"##"+is_completed+"##"+importance+"\n";
    }

	public void setId(int id) {
		this.id = id;
		
	}
	public String toString(TodoItem it) {
		return it.getId()+" "+it.getWriter()+" ["+it.getCategory()+"] "+it.getTitle()+"("+Strings.repeat("★", it.getImportance())+") "+it.getMemo()+" ["+it.getCurrent_date()+" - "+it.getDue_date()+"] "+Strings.repeat("✓", it.getIs_completed());	
	}
	public int getId() {
		return id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getIs_completed() {
		return is_completed;
	}
	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
}
