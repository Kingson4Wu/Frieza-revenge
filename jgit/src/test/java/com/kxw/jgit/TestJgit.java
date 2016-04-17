package com.kxw.jgit;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.util.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by kxw on 2015/9/18.
     * {<a href='http://wiki.eclipse.org/JGit/User_Guide'>@link</a>}
 */
public class TestJgit {

    @Test
    public void test() throws IOException, GitAPIException {
        //在用户的账号配置了ssh，即可提交
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        String projectURL = System.getProperty("user.dir");
        Repository repository = builder.setGitDir(new File(projectURL.substring(0, projectURL.lastIndexOf("\\"))+"\\.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        Git git = new Git(repository);
        AddCommand add = git.add();
        add.addFilepattern(".").call();//git add .
        CommitCommand commit = git.commit();
        /**-Dusername=%teamcity.build.username%**/
        commit.setCommitter("Kingson_Wu", "Kingson_Wu@163.com");
        commit.setAuthor("Kingson_Wu","Kingson_Wu@163.com");
        commit.setAll(true);
        //commit.setCommitter(new PersonIdent(repository));
        RevCommit revCommit = commit.setMessage("use jgit").call();//git commit -m "use jgit"
        String commitId = revCommit.getId().name();
        System.out.println(commitId);
        PushCommand push = git.push();
        push.call();//git push
    }


    @Test
    public void testURL(){
        String url = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println(url);
        String projectURL = System.getProperty("user.dir");
        //System.out.println(projectURL.lastIndexOf("\\"));
        System.out.println(projectURL.substring(0, projectURL.lastIndexOf("\\"))+"\\.git");
    }


    public void testClone() throws GitAPIException, IOException {

        File file = new File("/apps/svr/source");

        CloneCommand clone = Git.cloneRepository();
        clone.setBare(false);
        clone.setCloneAllBranches(false);
        clone.setDirectory(file).setURI("git@github.com:Kingson4Wu/StandardProject4JavaWeb.git");
        clone.setBranch("branch_name").call();

        Git git = Git.open(file);
        AddCommand add = git.add();
        add.addFilepattern(".").call();//git add .
        CommitCommand commit = git.commit();
        //commit.setCommitter(userName, email);
        // commit.setAuthor(userName, email);
        commit.setAll(true);
        RevCommit revCommit = commit.setMessage("commit message").call();//git commit -m "use jgit"
        String commitId = revCommit.getId().name();

    }

}
