import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface IMark {
    public void input();
    public void display();
}
class Student implements IMark {
    /**
     *
     */
    private static final PrintStream OUT = System.out;
    private String StuId;
    private String StuName;
    private String gender;
    private String birthday;

    public void setStuId(String stuID) {
        this.StuId = stuID;
    }
    public void setStuName(String stuName) {
        this.StuName = stuName;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }
    public void setbirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getStuId() {
        return this.StuId;
    }
    public String getStuName() {
        return this.StuName;
    }
    public String getgender() {
        return this.gender;
    }
    public String getbirthday() {
        return this.birthday;
    }
    public void input() {
        Scanner scanner = new Scanner(System.in);
        OUT.println("Mời nhập id, tên, giới tính, ngày sinh của sinh viên: ");
        this.StuId = scanner.nextLine();
        this.StuName = scanner.nextLine();
        this.gender = scanner.nextLine();
        this.birthday = scanner.nextLine();

    }
    public void display() {
        OUT.println("Id: " + this.StuId);
        OUT.println("Tên: " + this.StuName);
        OUT.println("Giới tính: " + this.gender);
        OUT.println("Ngày sinh: " + this.birthday);


    }

}
class StudentMark implements IMark {
    private String StuId;
    private String className;
    private String subjectName;
    private int semester;
    private float mark;

    public void setStuId(String stuID) {
        this.StuId = stuID;
    }
    public void setclassName(String className) {
        this.className = className;
    }
    public void setsubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public void setsemester(int semester) {
        this.semester = semester;
    }
    public void setmark(Float mark) {
        this.mark = mark;
    }
    public String getStuId() {
        return this.StuId;
    }
    public String getclassName() {
        return this.className;
    }
    public String getsubjectName() {
        return this.subjectName;
    }
    public int getsemester() {
        return this.semester;
    }
    public float getmark() {
        return this.mark;
    }
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời nhập id, tên lớp, tên môn học, kỳ học, điểm của sinh viên: ");
        this.StuId = scanner.nextLine();
        this.className = scanner.nextLine();
        this.subjectName = scanner.nextLine();
        this.semester = scanner.nextInt();
        this.mark = scanner.nextFloat();

    }
    public void display() {
        System.out.println("Id: " + this.StuId);
        System.out.println("Lớp: " + this.className);
        System.out.println("Môn học: " + this.subjectName);
        System.out.println("Kì học: " + this.semester);
        System.out.println("Điểm: " + this.mark);

    }
    

}
class StudentMarkTotal extends StudentMark{
    private String StuId;
    private int totalExamSubject;
    private float everageMark;

    public void setStuId(String stuID) {
        this.StuId = stuID;
    }
    public String getStuId() {
        return this.StuId;
    }
    //Tính tổng số môn thi
    public int getTotalExamSubject(ArrayList<StudentMark> list) {
        this.totalExamSubject = 0;
        for (StudentMark i: list ) {
            if (i.getStuId().equals(this.StuId)) {
                this.totalExamSubject +=1;
            }

        }
        return this.totalExamSubject;
    }
    //Tính điểm trung bình các môn thi
    public float calculateEverageMark(ArrayList<StudentMark> list) {
        int MarkTotal = 0;
        for (StudentMark i: list ) {
            if (i.getStuId().equals(this.StuId)) {
                MarkTotal += i.getmark();
            }
            
        }
        if ((float)this.getTotalExamSubject(list)!=0) {
            this.everageMark = (float)MarkTotal/(float)this.getTotalExamSubject(list);
        }
        else {
            this.everageMark = 0;
        }
        return this.everageMark;
    } 

    public void display() {
        System.out.println("Id: " + this.StuId);
        System.out.println("Số môn học: " + this.totalExamSubject);
        System.out.println("Điểm trung bình môn học: " + this.everageMark);
    }
}
public class Manager {
    public static void main(String[] args) {
        //Nhập thông tin 5 sinh viên
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> listStudent = new ArrayList<Student>();
        for (int i = 1; i < 6; i++) {
            Student s = new Student();
            s.input();
            listStudent.add(s);
        }
        //Khởi tạo danh sách id sinh viên
        ArrayList<String> listStudentId = new ArrayList<String>();
        for (Student i: listStudent ) {
            listStudentId.add(i.getStuId());
        }
        //Nhập bảng điểm, kiểm tra sinh viên có trong danh sách sinh viên
        ArrayList<StudentMark> listStudentMark = new ArrayList<StudentMark>();
        System.out.println("Nhập kích thước danh sách điểm: ");
        int siz = scanner.nextInt();
        for (int i = 1; i <= siz; i++) {
            StudentMark sm = new StudentMark();
            sm.input();
            if(!listStudentId.contains(sm.getStuId())) {
                i--;
                System.out.println("Sinh viên không có trong danh sách sinh viên, mời nhập lại");
            }
            else {
                listStudentMark.add(sm);
            }
        }
        //Sắp xếp sinh viên theo chữ cái đầu của tên
        Collections.sort(listStudent, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getStuName().compareTo(o2.getStuName());
            }
        }); 
        System.out.println("Danh sách sinh viên theo thứ tự tên sinh viên là: "); 
        for(Student i:listStudent) {
            i.display();
        }
        //Lấy thông tin điểm sinh viên theo id
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập ID sinh viên muốn lấy thông tin:");
        String id = input.nextLine();
        ArrayList<StudentMark> listStudentMarksById = new ArrayList<StudentMark>();
        for (StudentMark sm: listStudentMark) {
            if (sm.getStuId().equals(id)) {
                listStudentMarksById.add(sm);
            }
        }
        for (StudentMark sm: listStudentMarksById) {
            sm.display();
        }
        //Khởi tạo bảng điểm sinh viên bao gồm tổng số môn học và điểm trung bình môn
        ArrayList<StudentMarkTotal> listStudentMarksTotals = new ArrayList<StudentMarkTotal>();
        for (Student i: listStudent) {
            StudentMarkTotal st = new StudentMarkTotal();
            st.setStuId(i.getStuId());
            listStudentMarksTotals.add(st);
        }
        //Tìm sinh viên có điểm trung bình môn cao nhất và thấp nhất
        ArrayList<String> listStudentIdMax = new ArrayList<String>();
        ArrayList<String> listStudentIdMin = new ArrayList<String>();
        float maxMark = listStudentMarksTotals.get(0).calculateEverageMark(listStudentMark);
        float minMark = listStudentMarksTotals.get(0).calculateEverageMark(listStudentMark);
        for (StudentMarkTotal st: listStudentMarksTotals) {
            if(st.calculateEverageMark(listStudentMark) > maxMark) {
                maxMark = st.calculateEverageMark(listStudentMark);
            }
            if (st.calculateEverageMark(listStudentMark) < minMark) {
                minMark = st.calculateEverageMark(listStudentMark);
            }
        }
        for (StudentMarkTotal st: listStudentMarksTotals) {
            if (st.calculateEverageMark(listStudentMark) == maxMark) {
                listStudentIdMax.add(st.getStuId());
            }
            if (st.calculateEverageMark(listStudentMark) == minMark) {
                listStudentIdMin.add(st.getStuId());
            }
        }
        System.out.println("Sinh viên có điểm trung bình cao nhất là: ");
        for (Student i: listStudent) {
            if (listStudentIdMax.contains(i.getStuId())) {
                i.display();
            }
            
        }
        System.out.println("Danh sách sinh viên sau khi xóa sinh viên có điểm trung bình thấp nhất là: ");
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudentIdMin.contains(listStudent.get(i).getStuId())) {
                listStudent.remove(i);
                i--;                
            }
            else {
                listStudent.get(i).display();
            }
        }
        
        
        
    }

}
