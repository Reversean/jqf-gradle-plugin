package com.mborzenkov.jqfgradleplugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.gradle.api.Project;
import org.gradle.api.internal.tasks.DefaultSourceSet;
import org.gradle.api.internal.tasks.DefaultSourceSetContainer;
import org.gradle.api.tasks.SourceSetContainer;

/**
 * Utility functions for JQF.
 */
public class JqfUtil {

  /**
   * Returns a list of classpath elements with test and main sources.
   * @return list that contains
   *    main output dir
   *    test output dir
   *    classes dir
   */
  public static List<String> getTestClasspathElements(Project project) {
    DefaultSourceSetContainer sourceSets =
      (DefaultSourceSetContainer) project.getExtensions().getByName("sourceSets");
    String[] mainClasspath = getSourceSetRuntimeClasspath(sourceSets, "main");
    String[] testClasspath = getSourceSetRuntimeClasspath(sourceSets, "test");
    List<String> list = new ArrayList<>();
    Collections.addAll(list, mainClasspath);
    Collections.addAll(list, testClasspath);
    return list;
  }

  private static String[] getSourceSetRuntimeClasspath(SourceSetContainer container, String name) {
    DefaultSourceSet sourceSet = (DefaultSourceSet) container.getByName(name);
    String runtimeClasspath = sourceSet.getRuntimeClasspath().getAsPath();
    return runtimeClasspath.split(File.pathSeparator);
  }
}
