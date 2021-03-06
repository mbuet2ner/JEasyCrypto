# How to contribute efficiently

Sections covered in this file:

* [Reporting bugs or proposing features](#reporting-bugs-or-proposing-features)
* [Contributing pull requests](#contributing-pull-requests)
* [Communicating with developers](#communicating-with-developers)

**Please read the first section before reporting a bug!**

## Reporting bugs or proposing features

The golden rule is to **always open *one* issue for *one* bug**. If you notice
several bugs and want to report them, make sure to create one new issue for
each of them.

Everything referred to hereafter as "bug" also applies for feature requests.

If you are reporting a new issue, you will make our life much simpler (and the
fix come much sooner) by following these guidelines:

#### Search first in the existing database

Issues are often reported several times by various users. It's a good practice
to **search first** in the issues database before reporting your issue. If you
don't find a relevant match or if you are unsure, don't hesitate to **open a
new issue**. The bugsquad will handle it from there if it's a duplicate.

#### Specify the platform

JEasyCrypto runs on a large variety of platforms and operating systems and devices.
If you believe your issue is device/platform dependent (for example if it is
related to the rendering, crashes or compilation errors), please specify:
* Operating system
* Device (including architecture, e.g. x86, x86_64, arm, etc.)

#### Specify steps to reproduce

Many bugs can't be reproduced unless specific steps are taken. Please **specify
the exact steps** that must be taken to reproduce the condition, and try to
keep them as minimal as possible.

#### Provide a simple, example project

Sometimes an unexpected behavior happens in your project. In such case,
understand that:
* What happens to you may not happen to other users.
* We can't take the time to look at your project, understand how it is set up
  and then figure out why it's failing.

To speed up our work, please prepare for us **a simple project** that isolates
and reproduces the issue. This is always the **best way for us to fix it**.
You can attach a zip file with the minimal project directly to the bug report,
by drag and dropping the file in the GitHub edition field.

## Contributing pull requests

If you want to add new engine functionalities, please make sure that:

* This functionality is desired, which means that it solves a common use case
  that several users will need in their real-life projects.
* You talked to other developers on how to implement it best (on either
  communication channel, and maybe in a GitHub issue first before making your
  PR).
* Even if it does not get merged, your PR is useful for future work by another
  developer.

Similar rules can be applied when contributing bug fixes - it's always best to
discuss the implementation in the bug report first if you are not 100% about
what would be the best fix.

Some further information about pull PRs can be gathered [here](https://github.blog/2015-01-21-how-to-write-the-perfect-pull-request/).
Further information on how to fork a repository can be found [here](https://help.github.com/en/articles/fork-a-repo).

#### Claim an issue

To claim an issue for your group, please comment on one of the issues or open up a new one: first come, first served! If you will not provide a pull request for that issue after a reasonable amount of time, we may reassign your issue to another group.
Please also note, that very late claimings will not be accepted and the corresponding pull request will not be accepted!

#### Be nice to the git history

Try to make simple PRs that handle one specific topic. Just like for reporting
issues, it's better to open 3 different PRs that each address a different issue
than one big PR with three commits.

When updating your fork with upstream changes, please use ``git pull --rebase``
to avoid creating "merge commits". Those commits unnecessarily pollute the git
history when coming from PRs.

Also try to make commits that bring the engine from one stable state to another
stable state, i.e. if your first commit has a bug that you fixed in the second
commit, try to merge them together before making your pull request (see ``git
rebase -i`` and relevant help about rebasing or amending commits on the
Internet).

This git style guide has some good practices to have in mind:
[Git Style Guide](https://github.com/agis-/git-style-guide)

#### Format your commit logs with readability in mind

The way you format your commit logs is quite important to ensure that the
commit history and changelog will be easy to read and understand. A git commit
log is formatted as a short title (first line) and an extended description
(everything after the first line and an empty separation line).

The short title is the most important part, as it is what will appear in the
`shortlog` changelog (one line per commit, so no description shown) or in the
GitHub interface unless you click the "expand" button. As the name tells it,
try to keep that first line relatively short (ideally <= 50 chars, though it's
rare to be able to tell enough in so few characters, so you can go a bit
higher) - it should describe what the commit does globally, while details would
go in the description. Typically, if you can't keep the title short because you
have too much stuff to mention, it means that you should probably split your
changes in several commits :)

Here's an example of a well-formatted commit log (note how the extended
description is also manually wrapped at 80 chars for readability):

```
Prevent French fries carbonization by fixing heat regulation

When using the French fries frying module, JEasyCrypto would not regulate the heat
and thus bring the oil bath to supercritical liquid conditions, thus causing
unwanted side effects in the physics engine.

By fixing the regulation system via an added binding to the internal feature,
this commit now ensures that JEasyCrypto will not go past the ebullition temperature
of cooking oil under normal atmospheric conditions.

Fixes #1789, long live the Realm!
```

*Note:* When using the GitHub online editor (or worse, the drag and drop
feature), *please* edit the commit title to something meaningful. Commits named
"Update my_file.cpp" will not be accepted.

## Communicating with developers

The JEasyCrypto community has many communication
channels, some used more for user-level
discussions and support, others more for development discussions.

To communicate with developers (e.g. to discuss a feature you want to implement
or a bug you want to fix), the following channels can be used:
- [GitHub issues](https://github.com/mbuet2ner/JEasyCrypto/issues): If there is an
  existing issue about a topic you want to discuss, just add a comment to it -
  all developers watch the repository and will get an email notification. You
  can also create a new issue - please keep in mind to create issues only to
  discuss quite specific points about the development, and not general user
  feedback or support requests.
- [Slack channel](https://slack.com) - you will be invited.

Thanks!

The JEasyCrypto development team

______
Adapted from the  [Godot contributing guide](https://github.com/godotengine/godot/blob/master/CONTRIBUTING.md#contributing-pull-requests).
