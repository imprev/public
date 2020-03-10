To interview:

1) pull latest code from master (will have all relevant files deleted)

git checkout master
git pull

2) create a new branch using git:

git checkout -b interview-[persons_name]

3) cherry pick and push relevant commit (fb024014e903e35b604a88df83e2d0f7346207cf)

git cherry-pick fb024014e903e35b604a88df83e2d0f7346207cf
git push --set-upstream origin interview-[persons_name]

4) open a PR in github
