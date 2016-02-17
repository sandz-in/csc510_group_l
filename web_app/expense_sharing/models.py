from django.contrib.auth.models import AbstractBaseUser

from django.db import models

# Create your models here.
from expense_sharing.utils import MyUserManager


class User(AbstractBaseUser):
    email = models.EmailField(
        verbose_name='email address',
        max_length=255,
        unique=True,
    )
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)
    first_name = models.CharField(default=False, max_length=32)
    last_name = models.CharField(default=False, max_length=32)
    cell_no = models.CharField(default=False, max_length=32)

    objects = MyUserManager()

    USERNAME_FIELD = 'email'

    def get_full_name(self):
        # The user is identified by their email address
        return self.email

    def get_short_name(self):
        # The user is identified by their email address
        return self.email

    def __str__(self):  # __unicode__ on Python 2
        return self.email

    def has_perm(self, perm, obj=None):
        "Does the user have a specific permission?"
        # Simplest possible answer: Yes, always
        return True

    def has_module_perms(self, app_label):
        "Does the user have permissions to view the app `app_label`?"
        # Simplest possible answer: Yes, always
        return True

    @property
    def is_staff(self):
        "Is the user a member of staff?"
        # Simplest possible answer: All admins are staff
        return self.is_admin


class Expenses(models.Model):
    amount = models.FloatField()
    description = models.CharField(max_length=512)
    user = models.ForeignKey(User)
    currency = models.CharField(max_length=8)
    # image = models.ImageField()
    # notes = models.CharField(max_length=1024)
    added_on = models.DateTimeField(auto_now_add=True)
