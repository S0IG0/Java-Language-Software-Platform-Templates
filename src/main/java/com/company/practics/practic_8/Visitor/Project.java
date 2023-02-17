package com.company.practics.practic_8.Visitor;

public class Project implements ProjectElement{
    public ProjectElement[] projectElements;

    public Project(ProjectElement[] projectElements) {
        this.projectElements = projectElements;
    }

    @Override
    public void beWritten(Developer developer) {
        for (ProjectElement element : projectElements){
            element.beWritten(developer);
        }
    }
}
