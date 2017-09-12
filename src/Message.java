import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 2168241144406286820L;

	private int id;
	private String title;
	private String content;
	private int hits;
	private int categoryId;
	private String isdelete;
	private Date createdate;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String title, String content, int hits, int categoryId, String isdelete, Date createdate) {
		super();
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.categoryId = categoryId;
		this.isdelete = isdelete;
		this.createdate = createdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", content=" + content + ", hits=" + hits + ", categoryId="
				+ categoryId + ", isdelete=" + isdelete + ", createdate=" + createdate + "]";
	}

}
