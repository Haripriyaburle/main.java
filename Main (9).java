import java.util.*;
class Role {
    String name;
    List<Role> roles;
    Role(String name) {
        this.name = name;
        this.roles = new ArrayList<>();
    }
}
class User {
    String username;
    Role role;
    User(String username,Role role) {
        this.username = username;
        this.role = role;
    }
}

public class Main {
    public static void levelOrderTraversal(Role root) {
        if (root == null) {
            return;
        }
        Queue<Role> queue = new ArrayDeque<>();
        queue.add(root);
        Role curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            System.out.print(curr.name + ", ");
            curr.roles.forEach((role) -> {
                queue.add(role);
            });
        }
        System.out.println();
    } 
    public static Role getRole(Role root,String name) {
        if (root == null) {
            return null;
        }
        if(root.name.equals(name)) {
            return root;
        }
        for(Role role : root.roles) {
            Role temp = getRole(role,name);
            if(temp != null) {
                return temp;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        // Role root = new Role("CEO");
        // Role coo = new Role("COO");
        // Role cto = new Role("CTO");
        // root.roles.add(coo);
        // root.roles.add(cto);
        // Role spem = new Role("Senior Product Engineering Manager");
        // Role spmm = new Role("Senior Product Marketing Manager");
        // Role dot = new Role("Director of Technology");
        // Role ta = new Role("Technical Architect");
        // coo.roles.add(spem);
        // coo.roles.add(spmm);
        // cto.roles.add(dot);
        // dot.roles.add(ta);
        // Role me = new Role("Manager Engineering");
        // Role devops = new Role("DevOps");
        // Role developer = new Role("Developer");
        // Role tester = new Role("Tester");
        // spem.roles.add(me);
        // me.roles.add(devops);
        // me.roles.add(developer);
        // me.roles.add(tester);
        // Role mm = new Role("Manager Marketing");
        // Role ma = new Role("Marketing Analyst");
        // Role mexe = new Role("Marketing Executive");
        // spmm.roles.add(mm);
        // mm.roles.add(ma);
        // mm.roles.add(mexe);

        // levelOrderTraversal(root);
        System.out.print("Enter root role name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Role root = new Role(name);
        List<User> users = new ArrayList<User>();
        while(true) {
            System.out.println("Operations :");
            System.out.println("1. Add Sub Role.");
            System.out.println("2. Display Roles.");
            System.out.println("3. Delete Role.");
            System.out.println("4. Add User.");
            System.out.println("5. Display Users.");
            // System.out.println("6. Display Users and Sub Users.");
            System.out.print("Operation to be performed: ");
            int numRoles = Integer.parseInt(sc.nextLine());
            switch(numRoles) {
                case 1:
                    System.out.print("Enter sub role name: ");
                    String subRoleName = sc.nextLine();
                    Role subRole = new Role(subRoleName);
                    System.out.print("Enter reporting to role name: ");
                    String reportingToRoleName = sc.nextLine();
                    Role reportingToRole = getRole(root,reportingToRoleName);
                    if(reportingToRole != null) {
                        reportingToRole.roles.add(subRole);
                    }
                    break;
                case 2:
                    levelOrderTraversal(root);
                    break;
                case 3:
                    System.out.print("Enter the role to be deleted: ");
                    String rolename1 = sc.nextLine();
                    System.out.print("Enter the role to be transferred: ");
                    String rolename2 = sc.nextLine();
                    Role role1 = getRole(root,rolename1);
                    Role role2 = getRole(root,rolename2);
                    role1.name = role2.name;
                    role1.roles = role2.roles;

                    break;
                case 4:
                    System.out.print("Enter User Name: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Role: ");
                    String rolename = sc.nextLine();
                    Role role = getRole(root,rolename);                
                    User user = new User(username,role);
                    users.add(user);
                    break;
                case 5:
                    users.forEach((user1) -> {
                        System.out.println(user1.username + " - " + user1.role.name);
                    });
                    break;
                // case 6:
                //     users.forEach((user1) -> {
                //         Role role = user1.role;

                //     });
                //     break;
                default:
                    System.out.println("Invalid Operation");
                    break;
            }
        }
    }
}