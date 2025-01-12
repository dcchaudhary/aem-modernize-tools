---
layout: doc-page
title: Changes in v2.0
description: Details about what's new in v2.0 of the tools.
---

## Cloud Service Compatibility

<div class="video right">
  <span class="image object">
    <iframe src="https://video.tv.adobe.com/v/338822/?quality=12&learn=on" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen scrolling="no"></iframe>
  </span>
  <div class="content">
    <a href="https://video.tv.adobe.com/v/338822/?quality=12&learn=on" target="_blank" class="button primary small fit" title="Watch the Video">Learn More</a>
  </div>
</div>

The latest release of these tools is now AEM Cloud Service compatible. This has multiple connotations, but the most important of these is that the processes for conversions is done asynchronously using Sling Jobs.

When a user creates a configuration for any of the tools, that configuration is persisted in the repository and then a Sling Job is scheduled against it. Only one job can be scheduled at a time, but they are queued as soon as they are configured. Moving to Sling Jobs allows for use in the context of the AEM as a Cloud Service product.

To prevent overloading the system, depending on the number of paths submitted for processing, more than one Job may be scheduled. Each tool processes a specific set of paths, be it the page, design nodes, or individual components. If there are more than 500 paths to process, a job will be created for every 500 paths or portion there-of. So 1250 paths would create three scheduled jobs. 

In order for the jobs to run asynchronously, a *Service User* performs the transformations. This user has permissions to all the normal paths in the repository. To ensure that users do not schedule a conversion for which they do not have the appropriate permissions, a check is performed during the scheduling of the job. If the user requesting the job does not have rights to all the paths necessary, the job scheduling will fail. 


## All-in-One Conversion Tool

This version of the tool introduces a new feature, the All-in-One conversion. This tool performs the operations of the other three tools on every page submitted. By performing all the operations concurrently, additional capabilities are made available, most notably: applying policies to the Editable Template. 

More information on the All-in-One conversion tool can be found on its [about]({{ site.baseurl }}/pages/all-in-one/about.html) or [usage]({{ site.baseurl }}/pages/all-in-one/usage.html) page. 


## Page Handling

Another new feature of the All-in-One and Structure conversion tools, is page handling before processing. This is an option presented to the users to determine how a page should be pre-processed, before the conversion rules are applied. There are three options made available:

#### No Handling

This option will simply process the page as-is in place in the repository.

#### Restore

<p class="image right small">
    <img src="{{ site.baseurl }}/pages/structure/images/page-handling-restore.png" alt="Page Handling Restore Selected"/>
</p>

<div class="padded">
This option will restore a page to a previous state. When a page is processed using the All-in-One conversion or Structure conversion tools, a version is created prior to the execution.

This option will restore the page to the most recent version that was marked as "Pre-Modernization." This allows for reprocessing pages based on updated rules.
</div>

#### Copy to Target

<p class="image right small">
    <img src="{{ site.baseurl }}/pages/structure/images/page-handling-copy.png" alt="Page Handling Copy Selected"/>
</p>

This option will copy a page from the source location, to a new path in the repository. Each page must exist under the `source` path selection. When processing that portion of the path will be replaced with the `target` path.

The user scheduling the job must have *write* access to the target location in the repository, or the job will not be scheduled.

The target path must not have a page that already exists in that location, or that page conversion will fail.


## Dialog Conversion Tool

Since the intent of these tools is to transform _content_ and not code, the Dialog Conversion tool has been removed as of version 2.0. The tool is still available in v1.0. However, since its addition in this feature set caused confusion with other AEM modernization tool sets, it was removed to clarify intent.

## Advanced Component Rules

This update introduces the ability to aggregate or consolidate multiple components into a single component using just the Node based rule definitions. This allows teams to identify a specific sequence of components, that are better implemented as a single updated reference. 

More information on how to configure and a demonstration of its use can be found on the [Component Converter configuration]({{ site.baseurl }}/pages/component/config.html) page.

## Column Control Rule

In the previous release of the Modernization tools, the Column Control rewrite rule was considered a _Structure_ conversion. Thus it was applied when running the Page/Structure rewrite rules. This has been updated to be a _Component_ conversion. Therefore, updating a Column Control to a set of containers will only occur during either the All-in-One conversion, or the Component Conversion.

If during a job, you do not see the Column Control conversion being applied, check out the [Component Converter details]({{ site.baseurl }}/pages/component/details.html) page to see what may be occurring.  

## Paragraph Rewrite Rule

In the previous release of the Modernization there existed a _Structure_ rewrite rule to convert a Paragraph System (parsys) component to a Responsive Grid component. This feature has been removed from v2.0 of the tools. 

This was done as using th Responsive Grid directly in a project is considered an anti-pattern. Instead, teams should use a Container Core Component proxy. There is an example rule for accomplishing this documented on the [Component Converter details]({{ site.baseurl }}/pages/component/details.html) page.
