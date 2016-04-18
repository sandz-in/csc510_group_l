# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0009_expenses_duration'),
    ]

    operations = [
        migrations.AddField(
            model_name='expenses',
            name='amount_delete_keystroke',
            field=models.IntegerField(null=True),
        ),
        migrations.AddField(
            model_name='expenses',
            name='amount_others_keystroke',
            field=models.IntegerField(null=True),
        ),
        migrations.AddField(
            model_name='expenses',
            name='description_delete_keystroke',
            field=models.IntegerField(null=True),
        ),
        migrations.AddField(
            model_name='expenses',
            name='description_others_keystroke',
            field=models.IntegerField(null=True),
        ),
        migrations.AddField(
            model_name='expenses',
            name='initial_description',
            field=models.CharField(max_length=512, null=True),
        ),
    ]
