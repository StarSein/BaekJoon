import java.io.*;
import java.util.*;


public class Main {

    static class Student {
        String name;
        int birthDate;

        Student(String name, int birthDate) {
            this.name = name;
            this.birthDate = birthDate;
        }
    }
    static int n;
    static Student[] students;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 문자열로 된 생년월일을 정수로 변환한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        students = new Student[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            students[i] = new Student(name, 10000 * year + 100 * month + day);
        }

        // 학생들을 생년월일의 오름차순으로 정렬한다
        Arrays.sort(students, Comparator.comparingInt(e -> e.birthDate));

        // 첫째 학생과 마지막 학생의 이름을 출력한다
        System.out.println(students[n - 1].name + "\n" + students[0].name);
    }
}
